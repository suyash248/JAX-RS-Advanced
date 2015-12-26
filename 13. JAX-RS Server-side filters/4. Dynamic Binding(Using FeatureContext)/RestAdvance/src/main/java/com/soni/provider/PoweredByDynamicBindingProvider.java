package com.soni.provider;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import com.soni.annotation.SupportedBy;
import com.soni.provider.filter.PoweredByResponseFilter;
import com.soni.provider.filter.SupportedByResponseFilter;
import com.soni.resource.MyResource;

@Provider
public class PoweredByDynamicBindingProvider implements DynamicFeature {

	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		
		if( MyResource.class.equals(resourceInfo.getResourceClass())  ) {
			
			// If any resource method of MyResource resource class contains 'PoweredBy' string in it's name, 
			// PoweredByResponseFilter will be attached to that resource method.
			if( resourceInfo.getResourceMethod().getName().contains("PoweredBy") ) {
				System.out.println(resourceInfo.getResourceMethod().getName());
				context.register(PoweredByResponseFilter.class);
			} 
			
			// If any resource method of MyResource resource class is annotated with '@SupportedBy' annotation, 
			// SupportedByResponseFilter will be attached to that resource method.
			if(resourceInfo.getResourceMethod().isAnnotationPresent(SupportedBy.class)) {
				context.register(SupportedByResponseFilter.class);
			}
		}
	}

}
