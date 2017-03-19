package com.neaterbits.query.sql.dsl.api;

import static com.neaterbits.query.sql.dsl.api.IShortSelect.oneOrNull;


import static com.neaterbits.query.sql.dsl.api.IShortSelect.list;

import java.math.BigDecimal;
import java.util.function.Function;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.neaterbits.query.jpatest.model.Company;
import com.neaterbits.query.jpatest.model.Employee;
import com.neaterbits.query.jpatest.model.Person;
import com.neaterbits.query.sql.dsl.api.BaseJPATest;



public class ShortAPITest extends BaseJPATest {
	

	private static final ShortSelect select = com.neaterbits.query.sql.dsl.api.IShortSelect.get();
	
	//private static final QueryDataSource jpqlDS = jpql.getDataSource();
	
	

	/*
	private static final SinglePrepared<Company>
			acmeQuery2 = 
			 one(Company.class)
			.where(Company::getName).startsWith("Acme")
			
			.compile().prepare(jpqlJPA);
	*/
	

	@Test
    public void testLambdaIdentities() {
		
		final Function<Company, String> lambda1 = Company::getName;
		final Function<Company, String> lambda2 = Company::getName;
		
		System.out.format("Lambda 1: %08x\n", System.identityHashCode(lambda1));
		System.out.format("Lambda 2: %08x\n", System.identityHashCode(lambda2));
		
		System.out.println("equals: " + lambda1.equals(lambda2));
		System.out.println("hashCode: " + lambda1.hashCode() + "/" + lambda2.hashCode());
	}
	
	/*
	@Test
    public void testPrepared() {
		
		final Company c =  acmeQuery.execute();
		
		
		assertThat(c).isNull();
		
	}
	*/

	
	@Test
    public void testMapSumAndAvgOne() {
		
		final Company acme = new Company(1, "Acme", new BigDecimal("153.2"));
		final Company foo = new Company(2, "Foo", new BigDecimal("96.7"));
		
		
		final SingleBuilt<CompanyAggregatesVO> acmeQuery = select
				.one(CompanyAggregatesVO.class)

				//.map(Company::getName) .to (CompanyResultsVO::setName)
				.map().sum  ( Company::getStockPrice) .to (CompanyAggregatesVO::setSumStockPrice)


				.map().avg(Company::getStockPrice).to(CompanyAggregatesVO::setAvgStockPrice)

				.where(Company::getName).startsWith("Acme")
				.build();
		
		store(s  -> s.add(acme)
					 .add(foo)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		
	        		new CompanyAggregatesVO(null, 153.2, new BigDecimal("153.2")),
	        		acmeQuery,
	        		q -> q.execute());
		});
		
	}
	
	@Test
    public void testMapSumAndAvgList() {
		
		final Company acme1 = new Company(1, "Acme1", new BigDecimal("153.2"));
		final Company acme2 = new Company(2, "Acme2", new BigDecimal("96.7"));
		final Company foo = new Company(3, "Foo", new BigDecimal("35.6"));

		final SingleBuilt<CompanyAggregatesVO> acmeQuery = select
				.one(CompanyAggregatesVO.class)

				//.map(Company::getName) .to (CompanyResultsVO::setName)
				.map().sum(Company::getStockPrice).to (CompanyAggregatesVO::setSumStockPrice)


				.map().avg(Company::getStockPrice).to(CompanyAggregatesVO::setAvgStockPrice)

				.where(Company::getName).startsWith("Acme")
				.build();
		
		store(s  -> s.add(acme1)
					 .add(acme2)
					 .add(foo)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		new CompanyAggregatesVO(null, 124.95, new BigDecimal("249.9")),
	        		acmeQuery,
	        		q -> q.execute());
		});
		
	}

	@Test
    public void testPlusLiteral() {
		

		// Only tests that code compiles and query builds successfully
		final SingleBuilt<CompanySqrtAggregatesVO> acmeQuery = select
				.one(CompanySqrtAggregatesVO.class)
				.map(Company::getStockPrice)
				.plus(new BigDecimal("1.0"))
				.to(CompanySqrtAggregatesVO::setFoo)
				.build();
		
		
		acmeQuery.prepare(jpqlJPADerby).execute();
	}

	@Test
    public void testNestedPlus() {
		
		// Only tests that code compiles and query builds successfully
		final SingleBuilt<CompanySqrtAggregatesVO> acmeQuery = select
				.one(CompanySqrtAggregatesVO.class)
					.map(Company::getStockPrice)
						.plusOf(e -> e.abs(Company::getStockPrice).plus(new BigDecimal("983.2")))
						.to(CompanySqrtAggregatesVO::setFoo)
				.build();
		
		
		acmeQuery.prepare(jpqlJPADerby).execute();
	}


	@Test
    public void testVariousMappingSyntax() {
		
		final Company acme1 = new Company(1, "Acme1", new BigDecimal("45"));
		final Company acme2 = new Company(2, "Acme2", new BigDecimal("53"));
		final Company foo = new Company(3, "Foo", new BigDecimal("35.6"));

		// Only tests that code compiles and query builds successfully
		final SingleBuilt<CompanySqrtAggregatesVO> acmeQuery = select
				.one(CompanySqrtAggregatesVO.class)

				//.map(Company::getName) .to (CompanyResultsVO::setName)
				//.map(sqrt(sum(Company::getStockPrice))) .to (CompanySqrtAggregatesVO::setSumStockPrice)
				
				//.map().sqrt().sum(Company::getStockPrice) .to (CompanySqrtAggregatesVO::setSqrtSumStockPrice)

				//.map().sqrt().avg(Company::getStockPrice).to(CompanySqrtAggregatesVO::setSqrtAvgStockPrice)
				.map(Company::getStockPrice).plus(Company::getStockPrice).to(CompanySqrtAggregatesVO::setFoo)
				
				.map().sqrt(Company::getStockPrice).plus(Company::getStockPrice).plus(Company::getStockPrice).to(CompanySqrtAggregatesVO::setSqrtAvgStockPrice)
				
				.map()
						.sqrt().abs(Company::getStockPrice)
						
					. to(CompanySqrtAggregatesVO::setSqrtAvgStockPrice)
				
				.map(Company::getStockPrice)
						.plusOf(e -> e
										.abs(Company::getStockPrice)
										.plus(new BigDecimal("1.5")))
						
						
								.to(CompanySqrtAggregatesVO::setFoo)

				.map       (Company::getStockPrice)
				    .plusOf(
					  		e -> e  .abs   (Company::getStockPrice)
					  				.plus  (new BigDecimal("1.5"))
					  				.plusOf(e2 -> e2.sqrt(Company::getStockPrice))
								
							    )
				
					.to(CompanySqrtAggregatesVO::setFoo)

				//.map(Company::getStockPrice).plusOf(e -> e.abs()).to(CompanySqrtAggregatesVO::setFoo)
				.map(Company::getStockPrice)
						.plusOf(e -> e
									.abs()
									.abs(Company::getStockPrice))
						
						.to(CompanySqrtAggregatesVO::setFoo)
						
				.map(Company::getStockPrice).plusOf(e -> 
							e.abs().abs().abs(Company::getStockPrice)).to(CompanySqrtAggregatesVO::setFoo)
							
				//.map().sqrt().
				
				
				//.map(Company::getStockPrice).plus(Company::getStockPrice)
				
				
				//.map().sqrt().avg(field).

				.where(Company::getName).startsWith("Acme")
				.build();
		
		acmeQuery.prepare(jpqlJPADerby).execute();
	}
	
	
	
	@Test
    public void testSqrtOfAvgList() {
		
		final Company acme1 = new Company(1, "Acme1", new BigDecimal("45"));
		final Company acme2 = new Company(2, "Acme2", new BigDecimal("53"));
		final Company foo = new Company(3, "Foo", new BigDecimal("35.6"));

		final SingleBuilt<CompanySqrtAggregatesVO> acmeQuery = select
				.one(CompanySqrtAggregatesVO.class)

				.map().sqrt().avg(Company::getStockPrice).to(CompanySqrtAggregatesVO::setSqrtAvgStockPrice)

				.where(Company::getName).startsWith("Acme")
				.build();
		
		store(s  -> s.add(acme1)
					 .add(acme2)
					 .add(foo)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		new CompanySqrtAggregatesVO(7.0, null),
	        		acmeQuery,
	        		q -> q.execute());
		});
	}
	
	@Test
    public void testSumOfSqrtList() {
		
		final Company acme1 = new Company(1, "Acme1", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme2", new BigDecimal("121"));
		final Company acme3 = new Company(3, "Acme2", new BigDecimal("256"));
		final Company foo = new Company(4, "Foo", new BigDecimal("35.6"));

		final SingleBuilt<CompanySqrtAggregatesVO> acmeQuery = select
				.one(CompanySqrtAggregatesVO.class)

				//.map(Company::getName) .to (CompanyResultsVO::setName)
				
				/*
				 .map().sum().sqrt(Company::getStockPrice).plus(1) 
				 sum() eller sqrt() som er + 1? Bør nøste
				
				 .map().sum().sqrt(Company::getStockPrice).plus(1)
				
				funksjoner? rotete
				
				 map(plus(sum(sqrt(Company::getStockPrice)), 1)
				 map(sum(plus(sqrt(Company::getStockPrice), 1))
				 
				 Børe være infix.
				 
				 mapOf(s -> s.sumOf(e -> e.sqrt(Company::getStockPrice).plus(1) ))
				 
				 For ett-nivå? Men vanskelig å detektere statisk når et ett eller to nivåer? Må ha forskjellige retur-tuper, som er en del jobb.
				 Dvs må fjerne muligheten for å gjøre plus() om er mer enn en enkelt operasjon.

				 map().sum(Company::getStockPrice).plus(1).to(...)
				 
				 Alternativ: mapOf(s -> s.sum(Company::getStorPrice).plus(1) ).to()
				 
				 OK alternativ dette, og trengs unsett for mer enn ett level så da kan en begynne med dette og lage mere optimal versjon senere
				 for det enkle caset.

				 map().sum( s -> s.sqrt(Company::getStockPrice).plus(1) )
 				 map(s -> sum(e -> e.sqrt(Company::getStockPrice) ).plus(1) )

				OK, så her må vi ha egen signatur for map
				
				compute(s -> sum(e -> e.sqrt(Company::getStockPrice) ).plus(1))


				For de andre så spiller det ingen rolle? Siden disse ikke har parametere? Men de kan ha parametere som er 
				Company::getStockPrice f.eks. Så da funker ikke dette om ikke er consumer?
				
				Men må finne retur-typen som skal videre. Så hvordan finne denne? Anta at functions vi ikke finner når vi skanner er noe som skal eksekveres?
				Men har en builder som parameter. Må nok ha en egen funksjon her,
				
				sumOf()
				sqrtOf()
				
				computeSum()
				computeSqrt()

				compute(s -> sumOf(e -> e.sqrt(Company::getStockPrice) ).plus(1).minus(Company::getFoo))
				
				compute(s -> sum(Company::getStockPrice).plus(1).minus(Company::getFoo).plus(e -> e.sqrt(Company::getBar))

				compute(s -> sum(Company::getStockPrice).plus(1).minus().abs(Company::getFoo).plus().sqrt(Company::getBar))

				OBS! Kan ikke mikse aggregates og felter, f.eks
				
				compute(s -> sum(Company::getStockPrice).plus().avg(Company::getStockPrice)
				
				 er OK mens
				
				compute(s -> sum(Company::getStockPrice).plus(Company::getStockPrice))
				
				er ikke OK siden vi har blanding av aggregate og enkelt-entries i resultatet for rada.

				OBS! Compute-mode: med en gang er over i denne så 
				  - kan vi ikke kombinere multiple funksjoner
				  - alle funksjoner må enten ha en sub-compute eller ha et konkret parameter.
				  - alle arithmetics-fuksjoner er enten literal value, parameter (sjekk om er mulig), datafelt, eller sub-compute ("plusOf" ?)
				  
				  Vi kunne hatt evt mulihet for plus() med en enkelt funksjon etter på:
				  
				     sum(Company::getStockPrice).plus().avg(Company::getStockPrice)
				     
				     mere lesbart enn 
				     
				     
				     sum(Company::getStockPrice).plusOf(e -> e.avg(Company::getStockPrice))

					 siden egentlig er på samme nivå.
				     

				Dette blir litt tricky i API siden kan ha multiple nivåer men kan hente sub-query?
				Får modellere først med POJOer og ser hvordan det blir da.
				
				!! ISSUE !! Hvordan auto-detektere alias i nøsta nivåer? Vanskelig å sende dette opp igjen ?? Men må gjøres om skal være konsekevent.
				Evt at alltid må starte med et felt på noe vis? Men ikke så lett om skal gjøre operasjoner på nøsta nivå.
				
				Kan evt detektere miks build-time, at supplier-API er kalt for er et ganske isolert case dette, ikke ofte at skjer.
				
				Div eksempler:

				
				map().sumOf(s -> s.sqrt(Company::getStockPrice)).plus(1))
				mapOf(s -> s.sumOf(e -> e.sqrt(Company::getStockPrice).plus(1)))
				
				
				returnere en sum av to verdier i en kolonne?
				
				mapOf(s -> s.get(Dims::getWidth).mul(Dims::getHeight).mul(Dims::getLength)) . to(VO::setVolume)
				

 				 funksjoner? annen enklere mulighet om bare nøsta-kall, men dette blir i så fall egen dialekt, kan ikke mikse her, blir rot.
 				 
 				 map(sum(sqrt(Company::getStockPrice)).plus(1)))
 				 map(sum(sqrt(Company::getStockPrice).plus(1)))
 				 
 				 map(get(Dims::getWidth).mul(Dims::getHeight).mul(Dims::getLength)) . to(VO::setVolume)
				*/
				
				
				// map().sumOf(e -> e.sqrt(Company::getStock))
				
				.map().sum().sqrt(Company::getStockPrice).to (CompanySqrtAggregatesVO::setSqrtSumStockPrice)

				//.map().sqrt().avg(Company::getStockPrice).to(CompanySqrtAggregatesVO::setSqrtAvgStockPrice)

				.where(Company::getName).startsWith("Acme")
				.build();
		
		store(s  -> s.add(acme1)
					 .add(acme2)
					 .add(acme3)
					 .add(foo)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		new CompanySqrtAggregatesVO(null, 34.0),
	        		acmeQuery,
	        		q -> q.execute());
		});
	}
	
	//substring(1, s -> s.get(Company::getFoo).plus(1))

	
	@Test
    public void testArithmeticSubOf() {
		
		final Company acme1 = new Company(1, "Acme1", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme2", new BigDecimal("121"));
		final Company acme3 = new Company(3, "Acme2", new BigDecimal("256"));
		final Company foo = new Company(4, "Foo", new BigDecimal("35.6"));
		
		final SingleBuilt<CompanyResultVO> acmeQuery = select
				.one(CompanyResultVO.class)
				
				.map(Company::getStockPrice).to(CompanyResultVO::setStockPrice)
				.map()
						.abs()
						.absOfDecimal(
								e -> e.abs(Company::getStockPrice))

					.to(CompanyResultVO::setStockPrice)

				//.mapOf(e -> e.abs(c::getStockPrice)).to(CompanyAggregatesVO::setAvgStockPrice)

				.build();

		acmeQuery.prepare(jpqlJPADerby).execute();
	}

	
	@Test
    public void testArithmetic() {
		final SingleBuilt<CompanySqrtAggregatesVO> acmeQuery = select
				.one(CompanySqrtAggregatesVO.class)

				//.map(Company::getName) .to (CompanyResultsVO::setName)
				
				.map().sqrt().sum(Company::getStockPrice).to(CompanySqrtAggregatesVO::setSqrtSumStockPrice)

				//.map().sqrt().avg(Company::getStockPrice).to(CompanySqrtAggregatesVO::setSqrtAvgStockPrice)

				.where(Company::getName).startsWith("Acme")
				.build();
		
	}
		

	
	@Test
    public void testMapOf() {
		
		final Company acme1 = new Company(1, "Acme1", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme2", new BigDecimal("121"));
		final Company acme3 = new Company(3, "Acme2", new BigDecimal("256"));
		final Company foo = new Company(4, "Foo", new BigDecimal("35.6"));

		final Company c = select.alias(Company.class);
		
		final SingleBuilt<CompanyResultVO> acmeQuery = select
				.one(CompanyResultVO.class)
				
				.map(c::getStockPrice).to(CompanyResultVO::setStockPrice)
				//.mapOf(e -> e.abs(c::getStockPrice)).to(CompanyAggregatesVO::setAvgStockPrice)
				
				.build()
				;
	}

	@Test
    public void testPrepared() {
		final SinglePrepared<Company> acmeQuery = preparedJPQLDerby
				.one(Company.class)
				.where(Company::getName).startsWith("Acme")
				
				.build();
		
		
		
	}

	@Test
    public void testJoinEntity() {
		
		final Company acme1 = new Company(1, "Acme1", new BigDecimal("49"));
		final Company foo = new Company(2, "Foo", new BigDecimal("121"));

		final Person person1 = new Person(1, "John", "Smith");
		final Person person2 = new Person(2, "Angela", "Jones");
		final Person person3 = new Person(3, "Adam", "Jones");
		
		final Employee acmeEmp1 = new Employee(1, acme1, person1.getId());
		final Employee acmeEmp2 = new Employee(2, acme1, person2.getId());
		final Employee fooEmp1 = new Employee(3, foo, person3.getId());

		final SingleBuilt<Person> acmeQuery = select
				.one(Person.class)

				/*
				// reverse mapping
				.innerJoin(Employee::getCompany)

				 // on class? oin JPA
				.innerJoin(Employee.class)
				*/
				
				// innerJoin(Employee::getPersonId, Person::getId).and(Employee::getPersonId2, Person::getId2).sub(j -> j.)
				// innerjoin().on(Employee::getPersonId, Person::getId).and(Employee::getPersonId2, Person::getId2).sub()

				// Company is root of join tree
				//.joinRoot(Company.class) // TODO: always lambdas here to nest? more compex code
				
				// branch to person via employees
				.innerJoin(Company::getEmployees, j ->
					
						j.innerJoin(Employee::getPersonId, Person::getId))
				

				.where(Company::getName).startsWith("Foo")

				.build();
		
		
		store(s -> s
				.add(acme1)
				.add(foo)
				.add(person1).add(person2).add(person3)
				.add(acmeEmp1).add(acmeEmp2).add(fooEmp1))
		
		.check(ds -> checkSelectOneOrNull(
				ds,
				person3,
				acmeQuery,
				q -> q.execute())
		);
		
		
		// acmeQuery.prepare(nativeJPA);
		
	}
	
	@Test
    public void testJoinEntity2() {
		final SingleBuilt<Employee> acmeQuery = select
				.one(Employee.class)

				.innerJoin(Employee::getPersonId, Person::getId)

				.where(Company::getName).startsWith("Acme")
				.build();
		
	}
	
	
	
	@Test
    public void testNonPreparedAggregate() {

		final SinglePrepared<BigDecimal> prepared = select.sum(Company::getStockPrice).build().prepare(jpqlJPADerby);

		final BigDecimal value = prepared.execute();

		assertThat(value).isNotNull();
	}
	
	
	@Test
    public void testPreparedAggregate() {
		final BigDecimal ret = preparedJPQLDerby.sum(Company::getStockPrice).build().execute();
	}
	
	@Test
    public void testNameBasedMapped() {

		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

        final SingleBuilt<CompanyResultVO> startsWithAc =
        		oneOrNull(CompanyResultVO.class)

        	.map(Company::getName).to(CompanyResultVO::setName)

        	.where(Company::getName).startsWith("Ac")
        	.  and(Company::getName).endsWith("cme")

        	.build();
		
		store(s  -> s.add(acme)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		new CompanyResultVO(acme.getName()),
	        		startsWithAc,
	        		q -> q.execute());
		});

		// Search for foo as well, should return no matches
		store(s  -> s.add(foo)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		null,
	        		startsWithAc,
	        		q -> q.execute());
		});
	}
	
	@Test
    public void testSingleNameBasedEntity() {
		
		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

        final SingleBuilt<Company> startsWithAc =
        		oneOrNull(Company.class)

        	.where(Company::getName).startsWith("Ac")
        	.  and(Company::getName).endsWith("cme")

        	.build();
		
		store(s  -> s.add(acme)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		acme,
	        		startsWithAc,
	        		q -> q.execute());
		});

		// Search for foo as well, should return no matches
		store(s  -> s.add(foo)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		null,
	        		startsWithAc,
	        		q -> q.execute());
		});
	}
	
	@Test
    public void testListAllEntities() {
		
		final Company acme = new Company(1, "Acme");
		final Company foo = new Company(2, "Foo");

        final MultiBuilt<Company> startsWithAc = list(Company.class).build();
		
		store(s  -> s
				.add(acme)
				.add(foo))
				.
		check(ds -> {
	        checkSelectListUnordered(
	        		ds,
	        		startsWithAc,
	        		q -> q.execute(),
	        		acme,
	        		foo);
		});

		// Search for foo as well, should return no matches
		store(s  -> s.add(foo)).
		check(ds -> {
	        checkSelectListUnordered(
	        		ds,
	        		startsWithAc,
	        		q -> q.execute(),
	        		foo);
		});
	}

	@Test
    public void testListAllOrderByEntities() {
		
		final Company acme = new Company(1, "Acme");
		final Company foo = new Company(2, "Foo");

        final MultiBuilt<Company> startsWithAc =

    			select.list(Company.class)
        			 	.orderBy(Company::getName).desc()
        			.build();
		
		store(s  -> s
				.add(acme)
				.add(foo)).
		check(ds -> {
	        checkSelectListUnordered(
	        		ds,
	        		startsWithAc,
	        		q -> q.execute(),
	        		acme,
	        		foo);
		});

		// Search for foo as well, should return no matches
		store(s  -> s.add(foo)).
		check(ds -> {
	        checkSelectListUnordered(
	        		ds,
	        		startsWithAc,
	        		q -> q.execute(),
	        		foo);
		});
	}

	@Test
	public void testEntityWithName() {
		
		// Eg @Entity(name="land_plot") for LandPlot class does not work
		// since generates LandPlot in query
		
		assertThat(true).isEqualTo(false);
	}
	
	@Test
	public void testAggregateFunctionsWithNoParam() {
		
		// Eg @Entity(name="land_plot") for LandPlot class does not work
		// since generates LandPlot in query
		
		assertThat(true).isEqualTo(false);
	}
	
	
	@Test
	public void testAggregateFunctionsForShortPrepared() {
		
		// In ShortPrepare - does not implment aggregate results yet
		
		assertThat(true).isEqualTo(false);
	}
	
	
	@Test
	public void testAddedAllFieldTypesForAggregate() {
		
		// Aggregates must have BigInteger as well, and perhaps Float?
		
		assertThat(true).isEqualTo(false);
	}
	
	@Test
	public void testAddedAllFieldTypesForCount() {
		
		// IShared_Aggregate_Count_* interfaces must implement all field types
		
		assertThat(true).isEqualTo(false);
	}

	@Test
	public void testAddedAllFieldTypesForConditions() {
		
		// Conditions must have all field types
		
		assertThat(true).isEqualTo(false);
	}

	@Test
	public void testAddedAllFieldTypesForJoin() {
		
		// Joins must have all field types
		
		assertThat(true).isEqualTo(false);
	}


	@Test
	public void testOneQueryThrowsExceptionIfNoneOrMultiple() {
		
		// Joins must have all field types
		
		assertThat(true).isEqualTo(false);
	}

	@Test
	public void testNoNeedForJoinRootForEntities() {
		
		// Join root will be first join-clause
		
		assertThat(true).isEqualTo(false);
	}

	@Test
	public void testAssureAllRootLevelJoinClausesSameTypeForMapped() {
		
		// Join root must be same type for all joins at same level, only initial can be freely selected
		
		assertThat(true).isEqualTo(false);
	}
	
	@Test
	public void testAssureAllRootLevelJoinClausesSameTypeForEntity() {
		
		// Join root must be same type for all joins at same level, only initial can be freely selected
		
		assertThat(true).isEqualTo(false);
	}
	
	@Test
	public void testMapsCorrectAliasInMappedResult() {

		// If multiple aliases of same type in result, assure mapps correct one
		
		
		assertThat(true).isEqualTo(false);
	}

	@Test
	public void testThatChecksAliasJoinTypesAtBuildTime() {

		// Not able to statically typecheck that joins nest along the right types, but verify this at build time
		
		
		assertThat(true).isEqualTo(false);
	}
	
	@Test
	public void testThatChecksForCartesianJoinsAtBuildTime() {

		// Various checks that we do not include aliases that are not part of select sources
		// and that all select sources are joined on
		
		
		assertThat(true).isEqualTo(false);
	}
	
}
