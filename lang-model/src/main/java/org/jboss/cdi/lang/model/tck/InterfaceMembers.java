package org.jboss.cdi.lang.model.tck;

import jakarta.enterprise.lang.model.declarations.ClassInfo;

import java.lang.reflect.Modifier;

import static org.jboss.cdi.lang.model.tck.PlainClassMembers.Verifier.assertField;
import static org.jboss.cdi.lang.model.tck.PlainClassMembers.Verifier.assertMethod;

public interface InterfaceMembers {
    public static final String publicStaticFinalField = "";
    public static String publicStaticField = "";
    public final String publicFinalField = "";
    public String publicField = "";

    static final String staticFinalField = "";
    static String staticField = "";
    final String finalField = "";
    String field = "";

    public static void publicStaticMethod() {}
    static void staticMethod() {}
    private static void privateStaticMethod() {}

    public void publicAbstractMethod();
    void abstractMethod();

    public default void publicDefaultMethod() {}
    default void defaultMethod() {}

    private void privateMethod() {}

    class Verifier {
        public static void verify(ClassInfo clazz) {
            assert clazz.name().equals("org.jboss.cdi.lang.model.tck.InterfaceMembers");
            assert clazz.simpleName().equals("InterfaceMembers");

            assert !clazz.isPlainClass();
            assert clazz.isInterface();
            assert !clazz.isEnum();
            assert !clazz.isAnnotation();
            assert !clazz.isRecord();

            assert clazz.isAbstract();
            assert !clazz.isFinal();

            assert Modifier.isPublic(clazz.modifiers());
            assert !Modifier.isProtected(clazz.modifiers());
            assert !Modifier.isPrivate(clazz.modifiers());

            verifyFields(clazz);
            verifyMethods(clazz);
            assert clazz.constructors().isEmpty();
        }

        private static void verifyFields(ClassInfo clazz) {
            // 8 explicitly declared fields
            assert clazz.fields().size() == 8;

            assertField(clazz, "publicStaticFinalField", Modifier.PUBLIC, true, true, true);
            assertField(clazz, "publicStaticField", Modifier.PUBLIC, true, true, true);
            assertField(clazz, "publicFinalField", Modifier.PUBLIC, true, true, true);
            assertField(clazz, "publicField", Modifier.PUBLIC, true, true, true);

            assertField(clazz, "staticFinalField", Modifier.PUBLIC, true, true, true);
            assertField(clazz, "staticField", Modifier.PUBLIC, true, true, true);
            assertField(clazz, "finalField", Modifier.PUBLIC, true, true, true);
            assertField(clazz, "field", Modifier.PUBLIC, true, true, true);
        }

        private static void verifyMethods(ClassInfo clazz) {
            // 8 explicitly declared methods
            assert clazz.methods().size() == 8;

            assertMethod(clazz, "publicStaticMethod", Modifier.PUBLIC, true, false, false);
            assertMethod(clazz, "staticMethod", Modifier.PUBLIC, true, false, false);
            assertMethod(clazz, "privateStaticMethod", Modifier.PRIVATE, true, false, false);

            assertMethod(clazz, "publicAbstractMethod", Modifier.PUBLIC, false, false, true);
            assertMethod(clazz, "abstractMethod", Modifier.PUBLIC, false, false, true);

            assertMethod(clazz, "publicDefaultMethod", Modifier.PUBLIC, false, false, false);
            assertMethod(clazz, "defaultMethod", Modifier.PUBLIC, false, false, false);

            assertMethod(clazz, "privateMethod", Modifier.PRIVATE, false, false, false);
        }
    }
}
