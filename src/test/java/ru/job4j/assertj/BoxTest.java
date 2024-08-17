package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        assertThat(box.isExist()).isTrue();
        assertThat(box.whatsThis()).isEqualTo("Sphere");
        assertThat(box.getNumberOfVertices()).isEqualTo(0);
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 6);
        assertThat(box.isExist()).isTrue();
        assertThat(box.whatsThis()).isEqualTo("Tetrahedron");
        assertThat(box.getNumberOfVertices()).isEqualTo(4);
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 4);
        assertThat(box.isExist()).isTrue();
        assertThat(box.whatsThis()).isEqualTo("Cube");
        assertThat(box.getNumberOfVertices()).isEqualTo(8);
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(5, 6);
        assertThat(box.isExist()).isFalse();
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
        assertThat(box.getNumberOfVertices()).isEqualTo(-1);
    }

    @Test
    void IfSphereEdgeIs6ThenArea452Dot39() {
        Box box = new Box(0, 6);
        assertThat(box.getArea()).isEqualTo(452.39d, withPrecision(0.01d));
    }

    @Test
    void IfTetrahedronEdgeIs7ThenArea84Dot87() {
        Box box = new Box(4, 7);
        assertThat(box.getArea()).isEqualTo(84.87d, withPrecision(0.01d));
    }

    @Test
    void IfCubeEdgeIs9ThenArea486() {
        Box box = new Box(8, 9);
        assertThat(box.getArea()).isEqualTo(486d);
    }

    @Test
    void IfUnknownObjectThenArea0() {
        Box box = new Box(3, 10);
        assertThat(box.getArea()).isEqualTo(0);
    }
}