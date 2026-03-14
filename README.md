
## Run the application

It is two ways to run the application. One is that you run the whole application at once. But then you won´t have hot reloading and is not recommended if you are developing. Only use option 1 if you want to run the app and use it. 
### Option 1 — Docker
Run this command from the root folder of the project. (spond-club)
```bash
docker compose up --build
```

Then you can open http://localhost:5173 and use the wizard.

---

### Option 2 — Running frontend and backend separately 

- Java 21
- Maven
- Node.js 20 (can use higher version)
- Docker Desktop (for PostgreSQL)

**Step 1 — Start the database:**
```bash
docker compose up postgres -d
```

**Step 2 — Start the backend:**
```bash
cd backend/club
mvn spring-boot:run
```

**Step 3 — Run the frontend:**
```bash
cd frontend
npm install
npm run dev
```

Then open http://localhost:5173 in your browser.

---

## Running tests

Backend unit and integration tests:
```bash
cd backend/club
mvn test
```

---

## API endpoints

| Method | Endpoint | Description           |
|--------|----------|-----------------------|
| GET | `/api/forms/{formId}` | Get group details     |
| POST | `/api/forms/{formId}/registrations` | Submit a registration |

### Request examples:
Make sure the backend is running locally on http://localhost:8080
```bash
curl http://localhost:8080/api/forms/B171388180BC457D9887AD92B6CCFC86
```
```bash
curl -X POST "http://localhost:8080/api/forms/B171388180BC457D9887AD92B6CCFC86/registrations" \
  -H "Content-Type: application/json" \
  -d "{\"memberTypeId\":\"8FE4113D4E4020E0DCF887803A886981\",\"firstName\":\"Test\",\"lastName\":\"Test2\",\"email\":\"test@example.com\",\"phone\":\"+471111111\",\"birthDate\":\"1995-12-08\"}"
```

---

## Technology choices

### Frontend
- **React + Vite** 
- **TypeScript** 
- **Tailwind CSS**  
- **Axios**

### Backend
- **Spring Boot 3.2** 
- **Spring Validation** 
- **Flyway** 
- **JPA/Hibernate**

### Database
- **PostgreSQL** 
- **Docker**  

### Testing
- **JUnit 5 + Mockito** 
- **MockMvc** 

---

## Improvements
This application is very limited for now. There is couple of improvements I would make both technically and with features.
I have listed the next steps I would take with both features and technical. 
### Features
- **Authentication**: Admin login to manage the registrations. More secure, and limited for only those authenticated.
- **Admin dashboard**:  Dashboard for club admins to view, search and edit groups.
- **Email**: send a confirmation email to the user after successful registration. More practical for user interaction.
- **Multiple groups**: Make the ability to add more groups and let the admin user select which group they are registering new members for. 

### Technical
- **E2E tests**: Would add Cypress for full end-to-end testing
- **CI/CD pipeline**: Automate testing and deployment, for example with GitHub Actions
- **Environment variables**: move hardcoded values like form ID to environment configuration
- **Swagger**: Add swagger for documenting API endpoints.
- **Theming**: For improvement I would have made a theme that would we would be using for the UI. 
- **Remove hardcoding**: Currently the form ID is hardcoded in the frontend, could be dynamic via URL parameters and fetch based on the input

## Planning, reasoning and execution:
Before I started coding, I used some time to just make a overview of which technologies I wanted to use. 
I have a lot of experience with Spring Boot, and React. I wanted to add Vite, which I have good experiences with from earlier. 
Vite is known for fast developing and the hot reloading that you get. On top of that I used TypeScript which I always use when developing frontend.
As for the database I went with a relational database, PostgresQL suited good for our model.

I decided that I was going to build a monorepo, with a backend folder, frontend folder and a docker-compose.yml file at the root.
PostgreSQL is running in Docker so that anyone can spin up the database with a single command without installing PostgreSQL manually on the machine.
I used Flyway to manage the database. The reason for this is that Flyway runs SQL migration files automatically when the 
application starts. Created two migration files one to create the tables (groups, member_types, members) and one to seed the form data from Appendix 1.

After getting this up and running, with seeded tables. I started developing the backend. 
Models Group, Member and MemberType that matches with the database models. And then I went on to make the repositories, dto, services and controllers.
Next thing I did was unit test the service layer and the integration test for the controllers.

When the backend was up and running and tests was good. I started with setting up the frontend code. 
The frontend went smoothly I have already made a lot of Wizards from earlier projects and knew how I wanted to make it.
The first thing I did was fetching the group that was already seeded in the database. 
I wanted to fetch this first because I was going to use it in the first step in the wizard.
I used Axios for fetching the data from the API. 
After fetching the data I went on to make the components for the Wizard, with seperating the three steps into components. 
I used Tailwind CSS which gives a lot of good components with nice styling without too much customizing.
I followed this documentation https://v3.tailwindcss.com/docs/installation which I already know from earlier experience.








