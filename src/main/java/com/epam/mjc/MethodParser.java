package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */

    private final String OPEN_BRACKET = "\\(";
    private final String CLOSED_BRACKET = ")";
    private final String SPACE = " ";
    private final String COMMA_SPACE = ", ";

    public MethodSignature parseFunction(String signatureString) {

        String[] accessModifierReturnTypeMethodName_Arguments = signatureString.split(OPEN_BRACKET);

        String[] accessModifierReturnTypeMethodName = accessModifierReturnTypeMethodName_Arguments[0].split(SPACE);
        String accessModifier = accessModifierReturnTypeMethodName.length == 3 ? accessModifierReturnTypeMethodName[0] : null;
        String returnType = accessModifierReturnTypeMethodName.length == 3 ? accessModifierReturnTypeMethodName[1] : accessModifierReturnTypeMethodName[0];
        String methodName = accessModifierReturnTypeMethodName.length == 3 ? accessModifierReturnTypeMethodName[2] : accessModifierReturnTypeMethodName[1];

        List<MethodSignature.Argument> arguments = new ArrayList<>();
        String[] argumentType_Name = accessModifierReturnTypeMethodName_Arguments[1].replace(CLOSED_BRACKET, "").split(COMMA_SPACE);
        for (String argument : argumentType_Name) {
            if (!argument.isEmpty()) {
                String[] type_name = argument.split(SPACE);
                arguments.add(new MethodSignature.Argument(type_name[0], type_name[1]));
            }
        }
        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);

        return methodSignature;
    }
}
