package com.tiagodeveloper.springbootwithjwtsecurity.paraApagar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class FormattingMain {


    private interface Converter {
        default Function<Converter, String> definition() {
            final ObjectMapper mapper = new ObjectMapper();
            return input -> {
                try {System.out.println("Parrou no ObjectMapper");
                    return mapper.writeValueAsString(input);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            };
        }
        default String toJson() {
            return new Definition(this, definition()).execute();
        }
        default String toJson(Function<Converter, String> definition) {
            return new Definition(this, definition).execute();
        }
    }

    @Getter
    private static class Definition {
        private final Function<Converter, String> definition;
        private final Converter element;
        public Definition(Converter element, Function<Converter, String> definition) {
            this.element = element;
            this.definition = definition;
        }

        public String execute() {
            return definition.apply(element);
        }
    }


    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(FormattingMain.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();

    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void wellHelloThere() {
        final Function<Converter, String> definition = input -> {
//            System.out.println("Parrou no gson");
            return new Gson().toJson(input);
        };

        final var result = new Pessoa("Tiago", "Fonseca", 35)
                .toJson(definition);

//        System.out.println(result);
    }


    @Data
    @AllArgsConstructor
    public static class Person {
        private String name;
        private String lastName;
        private Integer age;
    }

    @Data
    @AllArgsConstructor
    public static class Pessoa implements Converter{
        private String name;
        private String lastName;
        private Integer age;
    }

}
