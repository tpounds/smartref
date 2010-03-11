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
public class SmartPhantomReference<T> extends PhantomReference<T> implements SmartReference
{
   public SmartPhantomReference(final T referent)
      { super(referent, PHANTOM_REF_QUEUE); }

   public void finalizeReferent()
      { /* do nothing */ }

   private final static ReferenceQueue PHANTOM_REF_QUEUE     = new ReferenceQueue();
   private final static Thread         PHANTOM_REF_FINALIZER = new SmartReferenceFinalizer("SmartPhantomReference Finalizer", PHANTOM_REF_QUEUE);

   static { PHANTOM_REF_FINALIZER.start(); }
}
