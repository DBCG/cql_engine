package org.opencds.cqf.cql.engine.execution;

import java.io.IOException;
import java.io.Reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import org.cqframework.cql.elm.execution.Element;
import org.cqframework.cql.elm.execution.Expression;
import org.cqframework.cql.elm.execution.Library;
import org.cqframework.cql.elm.execution.TypeSpecifier;
import org.opencds.cqf.cql.engine.elm.execution.LibraryWrapper;

public class JsonCqlLibraryReader {
    private JsonCqlLibraryReader() {
    }

    public static Library read(Reader reader) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        mapper.registerModule(module);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.setMixInAnnotation(Element.class, org.opencds.cqf.cql.engine.elm.execution.ElementMixin.class);
        simpleModule.setMixInAnnotation(Expression.class, org.opencds.cqf.cql.engine.elm.execution.ExpressionMixin.class);
        simpleModule.setMixInAnnotation(TypeSpecifier.class, org.opencds.cqf.cql.engine.elm.execution.TypeSpecifierMixin.class);
        //simpleModule.setMixInAnnotation(ExpressionDef.class, org.opencds.cqf.cql.elm.execution.ExpressionDefMixin.class);
        mapper.registerModule(simpleModule);
        Library result = mapper.readValue(reader, LibraryWrapper.class).getLibrary();
        return result;
    }
}
