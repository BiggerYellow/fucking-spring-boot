/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.context.annotation;

import java.util.Set;

import org.springframework.beans.factory.Aware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Interface that can be implemented by {@link ImportSelector} and
 * {@link ImportBeanDefinitionRegistrar} implementations when they can determine imports
 * early. The {@link ImportSelector} and {@link ImportBeanDefinitionRegistrar} interfaces
 * are quite flexible which can make it hard to tell exactly what bean definitions they
 * will add. This interface should be used when an implementation consistently results in
 * the same imports, given the same source.
 * 当ImportSelector和ImportBeanDefinitionRegister实现可以尽早确定导入时可以实现的接口。
 * ImportSelector和ImportBeanDefinitionRegister接口十分灵活，这使得很难准确的说出他们将添加哪些bean定义
 * 当一个实现一致地导致相同的导入(给定相同的源)时，应该使用此接口
 * <p>
 * Using {@link DeterminableImports} is particularly useful when working with Spring's
 * testing support. It allows for better generation of {@link ApplicationContext} cache
 * keys.
 * 当使用Spring的测试支持，使用DeterminableImports特别有用。他允许更好的生成ApplicationContext缓存
 * @author Phillip Webb
 * @author Andy Wilkinson
 * @since 1.5.0
 */
@FunctionalInterface
public interface DeterminableImports {

	/**
	 * Return a set of objects that represent the imports. Objects within the returned
	 * {@code Set} must implement a valid {@link Object#hashCode() hashCode} and
	 * {@link Object#equals(Object) equals}.
	 * 返回一组表示导入的对象。集合中返回的对象必须实现有效的hashCode和equals方法
	 * <p>
	 * Imports from multiple {@link DeterminableImports} instances may be combined by the
	 * caller to create a complete set.
	 * 调用者可以组合来自多个DeterminableImports实例的导入来创建一个完整的集合
	 * <p>
	 * Unlike {@link ImportSelector} and {@link ImportBeanDefinitionRegistrar} any
	 * {@link Aware} callbacks will not be invoked before this method is called.
	 * 与ImportSelector和ImportBeanDefinitionRegistrar不同，任何Aware回调函数在这个方法被调用前都不会被调用
	 * @param metadata the source meta-data
	 * @return a key representing the annotations that actually drive the import
	 */
	Set<Object> determineImports(AnnotationMetadata metadata);

}
