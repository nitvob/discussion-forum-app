# Discussion Site

## About

This project is a simple discussion site built using Spring Boot for the backend and React for the frontend. The primary features include:

- **REST API**: Allows clients to create topics, add comments, and reply to existing comments.
- **Integration with MongoDB**: All data is stored in a Mongo cluster.
- **HTTP Requests**: The React frontend interacts with the backend using Axios for HTTP requests.

## Set Up

To get the project up and running on your local machine, follow these steps:

### Prerequisites:

- Make sure you have `Node.js` and `npm` installed.
- Ensure `Java` and `Maven` are set up on your system.
- Have `MongoDB` installed and running.

### Steps:

1. **Clone the Repository**

   ```bash
   git clone https://github.com/nitvob/discussion_site.git
   ```

2. **Navigate to the Frontend Directory**

   ```bash
   cd "quora clone"/frontend
   ```

3. **Install Dependencies**

   ```bash
   npm install
   ```

4. **Navigate to the Backend Directory**

   ```bash
   cd ../backend
   ```

5. **Run the Spring Boot Application**
   ```bash
   mvn spring-boot:run
   ```

Once both the frontend and backend are running, you should be able to access the discussion site from your web browser.

---

Happy coding! 🚀
