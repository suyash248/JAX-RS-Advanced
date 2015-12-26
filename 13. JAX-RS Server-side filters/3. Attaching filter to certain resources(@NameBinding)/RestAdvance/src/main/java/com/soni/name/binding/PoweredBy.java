package com.soni.name.binding;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.ws.rs.NameBinding;

/**
 * @PoweredBy It is the name binding annotation.
 * If any filter(s)/interceptor(s) are annotated with this annotation. 
 * Those filter(s)/interceptor(s) will be associated to only those resource methods which are annotated with
 * <code>@PoweredBy</code>.
 * <p>And such filters/interceptors are known as <b><i>Name-Bound</i></b> filters/interceptors.</p>
 * @author Suyash Soni
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface PoweredBy {
}
