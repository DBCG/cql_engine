package org.opencds.cqf.cql.elm.execution;

import org.opencds.cqf.cql.exception.InvalidOperatorArgument;
import org.opencds.cqf.cql.execution.Context;

/*
Upper(argument String) String

The Upper operator returns the upper case of its argument.
If the argument is null, the result is null.
*/

public class UpperEvaluator extends org.cqframework.cql.elm.execution.Upper {

    public static Object upper(Object operand) {
        if (operand == null) {
            return null;
        }

        if (operand instanceof String) {
            //System.out.println("to Upper evaluator");
            return ((String) operand).toUpperCase();
        }

        throw new InvalidOperatorArgument(
                "Upper(String)",
                String.format("Upper(%s)", operand.getClass().getName())
        );
    }

    @Override
    protected Object internalEvaluate(Context context) {
        Object operand = getOperand().evaluate(context);
        return upper(operand);
    }
}
