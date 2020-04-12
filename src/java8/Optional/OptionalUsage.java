package java8.Optional;

import java.util.Optional;

public class OptionalUsage {
    public static void main(String[] args) {
        Optional<Insurance> insurance = Optional.empty();
        Optional<Insurance> insurance1 = Optional.of(new Insurance());
        insurance1.get();

        Optional<Insurance> objectOp = Optional.ofNullable(null);

        Insurance insurance2 = objectOp.orElseGet(Insurance::new);

        Insurance insurance3 = objectOp.orElse(new Insurance());

        Insurance insurance4 = objectOp.orElseThrow(RuntimeException::new);

        System.out.println(getInsuranceName(null));
        System.out.println(getinsuranceNameByOptional(null));
    }

    private static String getInsuranceName(Insurance insurance) {
        if (null == insurance)
            return "unknown";
        return insurance.getName();
    }

    private static String getinsuranceNameByOptional(Insurance insurance) {
        return Optional.ofNullable(insurance).map(Insurance::getName).orElse("unknown");
    }
}
