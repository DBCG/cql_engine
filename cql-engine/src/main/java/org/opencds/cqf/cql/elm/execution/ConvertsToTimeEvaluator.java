package org.opencds.cqf.cql.elm.execution;

import org.opencds.cqf.cql.exception.InvalidOperatorArgument;
import org.opencds.cqf.cql.execution.Context;
import org.opencds.cqf.cql.runtime.Time;

import java.time.format.DateTimeParseException;

/*

    ConvertsToTime(argument String) Time

    The ConvertsToTime operator returns true if its argument can be converted to a Time value. See the ToTime operator for
        a description of the supported conversions.

    If the input string is not formatted correctly, or does not represent a valid time-of-day value, the result is false.

    If the argument is null, the result is null.

*/

public class ConvertsToTimeEvaluator extends org.cqframework.cql.elm.execution.ConvertsToTime {

    public static Boolean convertsToTime(Object argument) {
        if (argument == null) {
            return null;
        }

        if (argument instanceof String) {
            try {
                new Time((String) argument);
            } catch (DateTimeParseException dtpe) {
                return false;
            }
            return true;
        }

        throw new InvalidOperatorArgument(
                "ConvertsToTime(String)",
                String.format("ConvertsToTime(%s)", argument.getClass().getName())
        );
    }

    @Override
    protected Object internalEvaluate(Context context) {
        Object operand = getOperand().evaluate(context);
        return convertsToTime(operand);
    }
}
