**Data JPA Concepts**
1. Spring Data commons
2. JpaRepository (public interface CustomerRepository extends JpaRepository<Customer, Long>)
3. Paging and Sorting

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


**Advice**
- (List<CustomObject>).containsAll(Collection<CustomObject>) method evaluates to true only 
when the CustomObject implements Equals and Hashcode method
-

