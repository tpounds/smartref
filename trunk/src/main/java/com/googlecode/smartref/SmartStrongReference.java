/**
 * Copyright (c) 2010 Trevor Pounds
 * Licensed under the terms of the MIT License: http://www.opensource.org/licenses/mit-license.php
 */
package com.googlecode.smartref;

import com.googlecode.smartref.internal.SmartReferenceFinalizer;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author Trevor Pounds
 */
public class SmartStrongReference<T> extends PhantomReference<T> implements SmartReference
{
   private final T referent;

   public SmartStrongReference(final T referent)
   {
      super(referent, STRONG_REF_QUEUE);
      this.referent = referent;
   }

   public void finalizeReferent()
      { /* do nothing */ }

   @Override final void finalize() throws Throwable
   {
      super.clear();
      this.referent = null;
      super.finalize();
   }

   private final static ReferenceQueue STRONG_REF_QUEUE     = new ReferenceQueue();
   private final static Thread         STRONG_REF_FINALIZER = new SmartReferenceFinalizer("SmartStrongReference Finalizer", STRONG_REF_QUEUE);

   static { STRONG_REF_FINALIZER.start(); }
}
