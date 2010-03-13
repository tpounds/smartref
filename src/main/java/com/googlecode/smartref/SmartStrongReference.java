/**
 * Copyright (c) 2010 Trevor Pounds
 * Licensed under the terms of the MIT License: http://www.opensource.org/licenses/mit-license.php
 */
package com.googlecode.smartref;

import java.lang.ref.PhantomReference;

/**
 * @author Trevor Pounds
 */
public class SmartStrongReference<T> extends PhantomReference<T> implements SmartReference
{
   private T referent;

   public SmartStrongReference(final T referent)
      { this(referent, STRONG_REF_QUEUE); }

   public SmartStrongReference(final T referent, final SmartReferenceQueue<? super T> queue)
   {
      super(referent, queue);
      this.referent = referent;
   }

   @Override public void clear()
      { referent = null; }

   @Override public T get()
      { return referent; }

   @Override protected final void finalize() throws Throwable
   {
      enqueue();
      super.finalize();
   }

   public void finalizeReferent()
      { /* do nothing */ }

   private final static SmartReferenceQueue<Object> STRONG_REF_QUEUE = new SmartReferenceQueue();
}
