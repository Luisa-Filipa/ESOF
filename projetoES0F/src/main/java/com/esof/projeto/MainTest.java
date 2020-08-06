package com.esof.projeto;

public class MainTest {

    private static void isPT(int port) {
       /* String collegeName = "faculdade";
        String degreeName = "curso";
        String explainerName = "explicador";
        String appointmentName = "Explicacao";

        Faculdade college = new Faculdade(collegeName);
        Curso degree = new Curso(degreeName);
        Explicador explainer = new Explicador(explainerName);
        Explicacao appointment = new Explicacao(explainer);

        String baseUrl = "http://localhost:" + port + "/";
        Logger logger = LoggerFactory.getLogger("main");
        RestTemplate restTemplate = new RestTemplate();

        logger.info("Start test: create new College. Should be successful");


        ResponseEntity<Faculdade> collegeResponseEntity = restTemplate.postForEntity(baseUrl + "/" + collegeName, college, Faculdade.class);
        assertTrue(collegeResponseEntity.getStatusCode().is2xxSuccessful());
        assertNotNull(collegeResponseEntity.getBody());
        assertNotNull(collegeResponseEntity.getBody().getId());

        logger.info("End test: create new College");

        logger.info("Start test: try to create existing College. Should fail");

        try {
            restTemplate.postForEntity(baseUrl + "/" + collegeName, college, Faculdade.class);
        } catch (HttpClientErrorException ignored) {

        }
        logger.info("End test: try to create existing College");

        logger.info("Start test: create new Degree. Should be successful");

        ResponseEntity<Curso> degreeResponseEntity = restTemplate.postForEntity(baseUrl + "/" + degreeName + "/" + collegeName, degree, Curso.class);
        assertTrue(degreeResponseEntity.getStatusCode().is2xxSuccessful());
        assertNotNull(degreeResponseEntity.getBody());
        assertNotNull(degreeResponseEntity.getBody().getId());

        logger.info("End test: create new Degree");

        logger.info("Start test: try to create existing College. Should fail");

        try {
            restTemplate.postForEntity(baseUrl + "/" + degreeName + "/" + collegeName, degree, Curso.class);
        } catch (HttpClientErrorException ignored) {

        }

        logger.info("End test: try to create existing College");

        logger.info("Start test: create new Explainer. Should be successful");

        ResponseEntity<Explicador> explainerResponseEntity = restTemplate.postForEntity(baseUrl + "/" + explainerName, explainer, Explicador.class);
        Explicador resultExplainer = explainerResponseEntity.getBody();
        assertNotNull(resultExplainer);
        assertNotNull(resultExplainer.getId());

        logger.info("End test: create new Explainer");

        logger.info("Start test: try to create existing Explainer. Should fail");


        try {
            restTemplate.postForEntity(baseUrl + "/" + explainerName, explainer, Explicador.class);
        } catch (HttpClientErrorException ignored) {

        }

        logger.info("End test: try to create existing Explainer");

        logger.info("Start test: Associate explainer to a degree. Should be successful");

        HttpEntity<Explicador> explainerHttpEntity = new HttpEntity<>(explainer);
        explainerResponseEntity = restTemplate.exchange(baseUrl + "/" + explainerName + "/" + degreeName, HttpMethod.PUT, explainerHttpEntity, Explicador.class);
        assertTrue(explainerResponseEntity.getStatusCode().is2xxSuccessful());
        assertNotNull(explainerResponseEntity.getBody());
        assertNotNull(explainerResponseEntity.getBody().getCurso());

        explainerResponseEntity = restTemplate.getForEntity(baseUrl + "/" + explainerName + "/" + explainerName, Explicador.class);
        assertTrue(explainerResponseEntity.getStatusCode().is2xxSuccessful());
        assertNotNull(explainerResponseEntity.getBody());
        assertNotNull(explainerResponseEntity.getBody().getCurso());

        logger.info("End test: Associate explainer to a degree. Should be successful");

        logger.info("Start test: Associate a non existing explainer to a existing degree. Should fail");


        try {
            restTemplate.exchange(baseUrl + "/" + explainerName + "/" + degreeName, HttpMethod.PUT, new HttpEntity<>(new Explicador("some explainer")), Explicador.class);
        } catch (HttpClientErrorException ignored) {

        }

        logger.info("End test: Associate a non existing explainer to a existing degree");

        logger.info("Start test: Associate an existing explainer to a non existing degree. Should fail");

        try {
            restTemplate.exchange(baseUrl + "/" + explainerName + "/someDegree", HttpMethod.PUT, explainerHttpEntity, Explicador.class);
        } catch (HttpClientErrorException ignored) {

        }

        logger.info("End test: Associate an existing explainer to a non existing degree");


        logger.info("Start test: Get all explainers. Should be successful");

        ResponseEntity<List> responseEntity = restTemplate.getForEntity(baseUrl + "/" + explainerName, List.class);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertNotNull(responseEntity.getBody());
        assertFalse(responseEntity.getBody().isEmpty());
        assertEquals(1, responseEntity.getBody().size());

        logger.info("End test: Get all explainers");


        logger.info("Start test: Add some availability to explainer. Should be successful");

        Disponibilidade schedule1 = new Disponibilidade();
        schedule1.setDiaDaSemana(LocalDate.now().getDayOfWeek());
        schedule1.setInicio(LocalTime.of(10, 0));
        schedule1.setFim(LocalTime.of(12, 0));

        explainer.addDisponibilidade(schedule1);

        explainerResponseEntity = restTemplate.exchange(baseUrl + "/" + explainerName, HttpMethod.PUT, new HttpEntity<>(explainer), Explicador.class);
        assertTrue(explainerResponseEntity.getStatusCode().is2xxSuccessful());
        assertNotNull(explainerResponseEntity.getBody());
        assertNotNull(explainerResponseEntity.getBody().getDisponibilidades());
        assertEquals(1, explainerResponseEntity.getBody().getDisponibilidades().size());

        logger.info("End test: Add some availability to explainer");

        logger.info("Start test: Create a new appointment. Should be successful");

        appointment.setInicio(LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
        appointment.setFim(LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));


        ResponseEntity<Explicacao> appointmentResponseEntity = restTemplate.postForEntity(baseUrl + "/" + appointmentName, appointment, Explicacao.class);
        assertTrue(appointmentResponseEntity.getStatusCode().is2xxSuccessful());
        assertNotNull(appointmentResponseEntity.getBody());

        logger.info("End test: Create a new appointment");

        logger.info("Start test: Create invalid appointments. Should fail");

        Explicacao invalidAppointment = new Explicacao();
        invalidAppointment.setExplicador(explainer);
        invalidAppointment.setInicio(LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)));
        invalidAppointment.setFim(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));

        try {
            restTemplate.postForEntity(baseUrl + "/" + appointmentName, invalidAppointment, Explicacao.class);
        } catch (HttpClientErrorException ignored) {

        }

        invalidAppointment.setExplicador(explainer);
        invalidAppointment.setInicio(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        invalidAppointment.setFim(LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));

        try {
            restTemplate.postForEntity(baseUrl + "/" + appointmentName, invalidAppointment, Explicacao.class);
        } catch (HttpClientErrorException ignored) {

        }

        invalidAppointment.setExplicador(explainer);
        invalidAppointment.setInicio(LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
        invalidAppointment.setFim(LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 0)));

        try {
            restTemplate.postForEntity(baseUrl + "/" + appointmentName, invalidAppointment, Explicacao.class);
        } catch (HttpClientErrorException ignored) {

        }

        invalidAppointment.setExplicador(explainer);
        invalidAppointment.setInicio(LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));
        invalidAppointment.setFim(LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 0)));

        try {
            restTemplate.postForEntity(baseUrl + "/" + appointmentName, invalidAppointment, Explicacao.class);
        } catch (HttpClientErrorException ignored) {

        }

        logger.info("End test: Create invalid appointments");
        logger.info("All tests OK");
*/
    }


    public static void main(String[] args) {

        int port = 8080;
        boolean isPT = true;
        if (args.length == 1) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }

        if (args.length > 1) {
            isPT = args[1].equalsIgnoreCase("pt");
        }


        if (isPT) {
            isPT(port);
        }
    }
}
