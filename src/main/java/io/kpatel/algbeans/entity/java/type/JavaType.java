package io.kpatel.algbeans.entity.java.type;

/**
 *  The interface represents all type in Java
 *
 *  This interface guarantees that Object.toString() while yield a correct translate to Java Code.
 *
 *  This guarantee is predominately used in the template-based source code generation
 *     where one just needs a valid type string regardless of how the type was constructed.
 *
 *  Object.toString() (over a "JavaType.toCode()") was selected due to String.format(String, Object) (in code templating)
 *     , StringBuilder.append(Object) (in code string-list making), template-based source code generation conversions
 *     employing it as a default conversion method.
 */
public interface JavaType{

}
