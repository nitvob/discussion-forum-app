# Discussion Site

## About

This project is a simple discussion site built using Spring Boot for the backend and React for the frontend. The primary features include:

- **REST API**: Enables clients to create topics, add comments, and reply to existing comments.
- **Integration with MongoDB**: Stores all data in a Mongo cluster.
- **HTTP Requests**: The React frontend communicates with the backend using Axios for HTTP requests.

## Set Up

To get the project up and running on your local machine, follow these steps:

### Prerequisites

- Ensure you have `Node.js` and `npm` installed.
- Confirm `Java` and `Maven` are set up on your system.
- Have `MongoDB` installed and running.

### Steps

1. **Clone the Repository**

   ```bash
   git clone https://github.com/nitvob/discussion_site.git
   ```

2. **Navigate to the Frontend Directory**

   ```bash
   cd "discussion_site"/frontend
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

## Knowledge and Skills Gained

Through the development of this project, I honed my ability to design and implement full-stack applications. I gained hands-on experience with building REST APIs in Spring Boot and integrating them with a dynamic React frontend. I deepened my understanding of MongoDB and its role in managing non-relational data effectively. Additionally, I sharpened my skills in configuring HTTP requests using Axios to ensure seamless communication between the frontend and backend.

This project strengthened my problem-solving abilities, particularly in debugging multi-layered systems and optimizing data flow. I also gained valuable insights into software development best practices, such as structuring a codebase for maintainability and scalability. Overall, this experience enhanced my technical expertise and reinforced my passion for creating robust, user-friendly applications.
