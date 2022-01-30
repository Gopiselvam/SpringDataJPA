**Data JPA Concepts**
1. Spring Data commons
2. JpaRepository (public interface CustomerRepository extends JpaRepository<Customer, Long>)
3. Paging and Sorting
4. findBy methods
5. @NamedQuery
6. @Query
7. @Transactional
8. Custom repository
9. MongoRepository
10. H2 database

**Java Tech stacks**

- lombok for Getters and Setters (org.projectlombok)
- JDBC
- JUnit
- loading CSV file into DB using opencsv
- soring a list using stream
- list.subList(fromIndex, toIndexExclusive)
- creating Pageable pageable = PageRequest.of(offset, pageSize);
- PagedListHolder (to manually verify the paging)
- List<CustomerDTO> sortedList = list.stream()
                .sorted(Comparator.comparing(CustomerDTO::getPhoneNumber))
                .collect(Collectors.toList());
- used @Sql() tag to load the Database before each test method
- use scema.sql for creating DB. and data.sql for loading data in DB
- alternatively we can define the custom filename and configure the following at class level
	import org.springframework.test.context.jdbc.*
	@SqlGroup({
        @Sql(scripts = {"/import_credit_customers.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = {"/truncate_credit_customer.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	})


**Advice**
- (List<CustomObject>).containsAll(Collection<CustomObject>) method evaluates to true only 
when the CustomObject implements Equals and Hashcode method
- Spring oot JPA will automatically convert the column addressId to address_id while creating tables automatically
- Make sure to override Equals and Hashcode method while comparing objects via assertEquals()
- the mthodName parameters should be sequencially in order with input parameters. Eg: findByEmailAndContactNumber(contactNumber, email) is incorrect.
The correct way is findByEmailAndContactNumber(email, contactNumber)

- Make sure to use @Transactional @Modifying before DML queries (delete, update)
- make sure to import org.springframework.transaction.annotation.Transactional;
- Use MySQL8Dialect to use innoDB to create Tables
- use MySQL8Dialect for mysql (spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect)

