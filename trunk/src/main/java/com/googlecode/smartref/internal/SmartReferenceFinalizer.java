/**
 * Copyright (c) 2010 Trevor Pounds
 * Licensed under the terms of the MIT License: http://www.opensource.org/licenses/mit-license.php
 */
package com.googlecode.smartref.internal;

import com.googlecode.smartref.SmartReference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * @author Trevor Pounds
 */
public class SmartReferenceFinalizer extends Thread
{
   private final ReferenceQueue refQueue;

   public SmartReferenceFinalizer(final String name, final ReferenceQueue refQueue)
   {
      super(name);
      this.refQueue = refQueue;
      setDaemon(true);
      setPriority(MAX_PRIORITY);
   }

   @Override public void run()
   {
      try
      {
         while(true)
         {
            Reference<?> ref = refQueue.remove();
            if(ref instanceof SmartReference)
               { ((SmartReference) ref).finalizeReferent(); }
            else
            {
/* TODO: implement annotated finalization
               for(Method finalizer : ref.getClass().getDeclaredMethods())
               {
                  if(m.getAnnotation(Finalize.class))
                  {
                     if(m.getParameterTypes().length != 0)
                        { throw IllegalArgumentException(); }
                     m.invoke(ref);
                  }
               }
*/
            }
         }
      }
      catch(InterruptedException e)
         { /* ignore */ }
   }
}
