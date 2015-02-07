// Autogenerated AST node
package org.python.antlr.op;

import org.python.antlr.AST;
import org.python.antlr.base.operator;
import org.python.antlr.PythonTree;
import org.python.core.Py;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.PyType;
import org.python.expose.ExposedGet;
import org.python.expose.ExposedMethod;
import org.python.expose.ExposedNew;
import org.python.expose.ExposedSet;
import org.python.expose.ExposedType;

@ExposedType(name = "_ast.Sub", base = operator.class)
public class Sub extends PythonTree {
    public static final PyType TYPE = PyType.fromClass(Sub.class);

public Sub() {
}

public Sub(PyType subType) {
    super(subType);
}

@ExposedNew
@ExposedMethod
public void Sub___init__(PyObject[] args, String[] keywords) {}

    private final static PyString[] fields = new PyString[0];
    @ExposedGet(name = "_fields")
    public PyString[] get_fields() { return fields; }

    private final static PyString[] attributes = new PyString[0];
    @ExposedGet(name = "_attributes")
    public PyString[] get_attributes() { return attributes; }

    @ExposedMethod
    public PyObject __int__() {
        return Sub___int__();
    }

    final PyObject Sub___int__() {
        return Py.newInteger(2);
    }

}
