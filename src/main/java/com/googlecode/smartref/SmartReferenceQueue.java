/**
 * Copyright (c) 2010 Trevor Pounds
 * Licensed under the terms of the MIT License: http://www.opensource.org/licenses/mit-license.php
 */
package com.googlecode.smartref;

import com.googlecode.smartref.internal.SmartReferenceFinalizer;

import java.lang.ref.ReferenceQueue;

/**
 * @author Trevor Pounds
 */
public class SmartReferenceQueue<T> extends ReferenceQueue<T>
{
   private final Thread finalizer;

   public SmartReferenceQueue()
   {
      this.finalizer = new SmartReferenceFinalizer("SmartReferenceQueue Finalizer (" + System.identityHashCode(this) + ")", this);
      this.finalizer.start();
   }

   @Override protected final void finalize() throws Throwable
   {
      this.finalizer.interrupt();
      super.finalize();
   }
}
