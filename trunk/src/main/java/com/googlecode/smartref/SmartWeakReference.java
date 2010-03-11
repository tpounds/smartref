/**
 * Copyright (c) 2010 Trevor Pounds
 * Licensed under the terms of the MIT License: http://www.opensource.org/licenses/mit-license.php
 */
package com.googlecode.smartref;

import com.googlecode.smartref.internal.SmartReferenceFinalizer;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author Trevor Pounds
 */
public class SmartWeakReference<T> extends PhantomReference<T> implements SmartReference
{
   public SmartWeakReference(final T referent)
      { super(referent, WEAK_REF_QUEUE); }

   public void finalizeReferent()
      { /* do nothing */ }

   private final static ReferenceQueue WEAK_REF_QUEUE     = new ReferenceQueue();
   private final static Thread         WEAK_REF_FINALIZER = new SmartReferenceFinalizer("SmartWeakReference Finalizer", WEAK_REF_QUEUE);

   static { WEAK_REF_FINALIZER.start(); }
}
