/**
 * Copyright (c) 2010 Trevor Pounds
 * Licensed under the terms of the MIT License: http://www.opensource.org/licenses/mit-license.php
 */
package com.googlecode.smartref;

import com.googlecode.smartref.internal.SmartReferenceFinalizer;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @author Trevor Pounds
 */
public class SmartSoftReference<T> extends SoftReference<T> implements SmartReference
{
   public SmartSoftReference(final T referent)
      { super(referent, SOFT_REF_QUEUE); }

   public void finalizeReferent()
      { /* do nothing */ }

   private final static ReferenceQueue SOFT_REF_QUEUE     = new ReferenceQueue();
   private final static Thread         SOFT_REF_FINALIZER = new SmartReferenceFinalizer("SmartSoftReference Finalizer", SOFT_REF_QUEUE);

   static { SOFT_REF_FINALIZER.start(); }
}
