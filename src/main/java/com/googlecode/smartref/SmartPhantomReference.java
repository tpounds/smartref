/**
 * Copyright (c) 2010 Trevor Pounds
 * Licensed under the terms of the MIT License: http://www.opensource.org/licenses/mit-license.php
 */
package com.googlecode.smartref;

import java.lang.ref.PhantomReference;

/**
 * @author Trevor Pounds
 */
public class SmartPhantomReference<T> extends PhantomReference<T> implements SmartReference
{
   public SmartPhantomReference(final T referent)
      { super(referent, DEFAULT_PHANTOM_REF_QUEUE); }

   public SmartPhantomReference(final T referent, final SmartReferenceQueue<? super T> queue)
      { super(referent, queue); }

   public void finalizeReferent()
      { /* do nothing */ }

   private final static SmartReferenceQueue<Object> DEFAULT_PHANTOM_REF_QUEUE = new SmartReferenceQueue();
}
