<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/Mahzarasua/resumeApi-docker-modules">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Resume API docker Modules</h3>

  <p align="center">
    CRUD operations for Resume API
    <br />
    <a href="https://github.com/Mahzarasua/resumeApi-docker-modules/issues">Report Bug</a>
    ·
    <a href="https://github.com/Mahzarasua/resumeApi-docker-modules/issues">Request Feature</a>
  </p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->

## About The Project

This project was created using Java and Springboot, it will help you to see how it works with 2 different databases.

It is the same implementation of Resume API but adjusted to use Mongo as DB in one implementation and PostgresDB in the
other.

Most of the coding should be the same and you will find small differences in the way the Models and Repositories were
created.

The service interacts with Docker, a compose file has been included to get the images required for the services to work
out of the box.

This implementation was done using Gradle, if you are interested on using Maven, you can find it [here](https://github.com/Mahzarasua/resumeApi-docker-modules) 

### Built With

* [Docker](https://docs.docker.com/get-docker/)
    * [PostgreSQL](https://www.postgresql.org/)
    * [pgAdmin](https://www.pgadmin.org/)
    * [MongoDB](https://www.mongodb.com/es)
* [Springboot](https://spring.io/)
* [Java](https://www.oracle.com/java/technologies/javase-downloads.html)
* [Intellij](https://www.jetbrains.com/idea/download/)
* [SB Banner Generator](https://devops.datenkollektiv.de/banner.txt/index.html)

<!-- GETTING STARTED -->

## Getting Started

To get a local copy up and running follow these simple steps.

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/Mahzarasua/resumeApi-docker-modules.git
   ```
2. Open the project with Intellij and do a Maven build to get the dependencies
   ```sh
   mvn clean install
   ```
3. Make sure Docker is installed and run your application, since the integration with Docker Compose is enabled, running
   the application should start the necessary images.

<!-- USAGE EXAMPLES -->

## Usage

A Postman collection is shared within the files of the project, however I am actively working on translating this collection to ApiDog. Being open source it does not have the limit of how many times you can run a collection and more testing can be achieved by this. I will update this section once I am done migrating it

<!-- ROADMAP -->

## Roadmap

See the [open issues](https://github.com/Mahzarasua/resumeApi-docker-modules/issues) for a list of proposed features (
and
known issues).



<!-- CONTRIBUTING -->

## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<!-- LICENSE -->

## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->

## Contact

Your Name - [@mahzarasua](https://twitter.com/mahzarasua) - mahzarasua@outlook.com

Project
Link: [https://github.com/Mahzarasua/resumeApi-docker-modules](https://github.com/Mahzarasua/resumeApi-docker-modules)



<!-- Acknowledgments -->

## Acknowledgments

* []()
* []()
* []()

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[contributors-shield]: https://img.shields.io/github/contributors/Mahzarasua/resume-api-modules.svg?style=for-the-badge

[contributors-url]: https://github.com/Mahzarasua/resumeApi-docker-modules/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/Mahzarasua/resume-api-modules.svg?style=for-the-badge

[forks-url]: https://github.com/Mahzarasua/resumeApi-docker-modules/network/members

[stars-shield]: https://img.shields.io/github/stars/Mahzarasua/resume-api-modules.svg?style=for-the-badge

[stars-url]: https://github.com/Mahzarasua/resumeApi-docker-modules/stargazers

[issues-shield]: https://img.shields.io/github/issues/Mahzarasua/resume-api-modules.svg?style=for-the-badge

[issues-url]: https://github.com/Mahzarasua/resumeApi-docker-modules/issues

[license-shield]: https://img.shields.io/github/license/Mahzarasua/resume-api-modules.svg?style=for-the-badge

[license-url]: https://github.com/Mahzarasua/resumeApi-docker-modules/blob/master/LICENSE.txt

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555

[linkedin-url]: https://linkedin.com/in/Mahzarasua
