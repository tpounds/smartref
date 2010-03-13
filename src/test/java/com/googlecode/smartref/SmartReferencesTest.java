/**
 * Copyright (c) 2010 Trevor Pounds
 * Licensed under the terms of the MIT License: http://www.opensource.org/licenses/mit-license.php
 */
package com.googlecode.smartref;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit test for {@link SmartReference} types.
 *
 * @author Trevor Pounds
 * @see {@link SmartPhantomReference}
 * @see {@link SmartSoftReference}
 * @see {@link SmartStrongReference}
 * @see {@link SmartWeakReference}
 */
public class SmartReferencesTest
{
   /**
    * Test to make sure {@link SmartReference} types are cleaned up and
    * the default {@link SmartReferenceQueue} for each impl invokes the
    * proper callback.
    *
    * NOTE:
    *
    *    Since soft references are cleared at the discreation of the JVM
    *    implementation we can not accurately test the reclaim logic for
    *    a {@link SmartSoftReference}. The only guarantee made by the JVM
    *    is that a softly-reachable object will be reclaimed before a
    *    java.lang.OutOfMemoryError would occur (i.e. low memory constraints).
    */
   @Test public void smartRefsWithDefaultReferenceQueue() throws Throwable
   {
      Object obj = new Object();

      final AtomicBoolean phantomRefFinalized = new AtomicBoolean(false);
      SmartReference phantomRef = new SmartPhantomReference<Object>(obj)
      {
         @Override public void finalizeReferent()
            { phantomRefFinalized.set(true); } 
      };

      final AtomicBoolean strongRefFinalized = new AtomicBoolean(false);
      SmartReference strongRef = new SmartStrongReference<Object>(new Object())
      {
         @Override public void finalizeReferent()
            { strongRefFinalized.set(true); } 
      };

      final AtomicBoolean weakRefFinalized = new AtomicBoolean(false);
      SmartReference weakRef = new SmartWeakReference<Object>(obj)
      {
         @Override public void finalizeReferent()
            { weakRefFinalized.set(true); } 
      };

      obj = null;
      strongRef = null;

      System.gc();

      Thread.sleep(5000);

      assertTrue("SmartPhantomReference referent not finalized!", phantomRefFinalized.get());
      assertTrue("SmartStrongReference referent not finalized!",  strongRefFinalized.get());
      assertTrue("SmartWeakReference referent not finalized!",    weakRefFinalized.get());
   }
}
