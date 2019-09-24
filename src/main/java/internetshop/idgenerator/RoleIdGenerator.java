package internetshop.idgenerator;

public class RoleIdGenerator {
    private static Long idGenerator = 0L;

    private RoleIdGenerator() {

    }

    public static Long getGeneratedId() {
        return idGenerator++;
    }
}
