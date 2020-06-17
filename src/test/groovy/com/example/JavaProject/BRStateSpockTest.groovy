package com.example.JavaProject

import com.example.JavaProject.enums.BRRegion
import com.example.JavaProject.enums.BRState
import spock.lang.*

import java.util.function.Function
import java.util.stream.Collectors

class BRStateSpockTest extends Specification {
    @Shared Map<BRRegion, List<BRState>> brStatesByRegion = BRState.stream()
            .collect(Collectors.groupingBy({ it.region } as Function))

    void "Brazil should have 27 states"() {
        expect:
            BRState.values().length == 27
    }

    def "Brazil should have 5 regions"() {
        expect:
            brStatesByRegion.size() == 5
    }

    def "Brazil should contains the correct number of states by region"() {
        expect:
            brStatesByRegion.get(BRRegion.N).size() == 7
            brStatesByRegion.get(BRRegion.NE).size() == 9
            brStatesByRegion.get(BRRegion.SE).size() == 4
            brStatesByRegion.get(BRRegion.S).size() == 3
            brStatesByRegion.get(BRRegion.CO).size() == 4
    }
}
