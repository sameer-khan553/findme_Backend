package findme_backend.missingperson.specification;


import findme_backend.missingperson.entity.MissingPerson;
import findme_backend.missingperson.enums.MissingStatus;
import org.springframework.data.jpa.domain.Specification;

public class MissingPersonSpecification {

    public static Specification<MissingPerson> hasName(String name) {

        return (root, query, cb) ->

                name == null || name.isBlank()
                        ? cb.conjunction()
                        : cb.like(
                                cb.lower(root.get("fullName")),
                                "%" + name.toLowerCase() + "%");
    }

    public static Specification<MissingPerson> hasCity(String city) {

        return (root, query, cb) ->

                city == null || city.isBlank()
                        ? cb.conjunction()
                        : cb.equal(
                                cb.lower(root.get("city")),
                                city.toLowerCase());
    }

    public static Specification<MissingPerson> hasStatus(MissingStatus status) {

        return (root, query, cb) ->

                status == null
                        ? cb.conjunction()
                        : cb.equal(root.get("status"), status);
    }
}
