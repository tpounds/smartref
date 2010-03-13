/**
 * Copyright (c) 2010 Trevor Pounds
 * Licensed under the terms of the MIT License: http://www.opensource.org/licenses/mit-license.php
 */
package com.googlecode.smartref;

import java.lang.ref.WeakReference;

/**
 * @author Trevor Pounds
 */
public class SmartWeakReference<T> extends WeakReference<T> implements SmartReference
{
   public SmartWeakReference(final T referent)
      { this(referent, DEFAULT_WEAK_REF_QUEUE); }

   public SmartWeakReference(final T referent, final SmartReferenceQueue<? super T> queue)
      { super(referent, queue); }

   public void finalizeReferent()
      { /* do nothing */ }

   private final static SmartReferenceQueue<Object> DEFAULT_WEAK_REF_QUEUE = new SmartReferenceQueue();
}
