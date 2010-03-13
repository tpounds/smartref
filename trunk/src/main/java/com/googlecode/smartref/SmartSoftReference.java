/**
 * Copyright (c) 2010 Trevor Pounds
 * Licensed under the terms of the MIT License: http://www.opensource.org/licenses/mit-license.php
 */
package com.googlecode.smartref;

import java.lang.ref.SoftReference;

/**
 * @author Trevor Pounds
 */
public class SmartSoftReference<T> extends SoftReference<T> implements SmartReference
{
   public SmartSoftReference(final T referent)
      { this(referent, DEFAULT_SOFT_REF_QUEUE); }

   public SmartSoftReference(final T referent, final SmartReferenceQueue<? super T> queue)
      { super(referent, queue); }

   public void finalizeReferent()
      { /* do nothing */ }

   private final static SmartReferenceQueue<Object> DEFAULT_SOFT_REF_QUEUE = new SmartReferenceQueue();
}
