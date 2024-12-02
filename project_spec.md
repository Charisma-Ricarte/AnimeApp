# **AnimeApp**

## Table of Contents

1. [App Overview](#App-Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
1. [Build Notes](#Build-Notes)

## App Overview

### Description 

AnimeApp is a cataloging application tailored for anime enthusiasts. It provides users with an intuitive interface to search, discover, and explore anime. The app offers lists like Shonen, Romance, and Adventure, along with personalized recommendations, quizzes, and an interactive aspect. By using the Jikan API, the app ensures accurate and up-to-date anime information.

### App Evaluation

- **Description:** An anime catalogue with search features, displays a few categories in the home pages like shonen,romance,ect.
- **Category** Social Networking, Entertainment, and Cataloging.
- **Mobile:** The app would work better then a website so that if we wanted we could add push notifcitations to keep users updated on new anime releases as well as making it more convenient as a an app instead on a website.
- **Story:** Users can browse and manage their anime catalog, discover trending shows, take quizzes, and connect with other anime fans.
- **Habit:** Users could frequently return to track their progress, explore recommendations, or engage in discussions. The inclusion of personality quizzes and user-generated lists makes the app even more engaging.
- **Scope:** Moderate to large since the base features of a catalog with search features is achievable. I think the biggest issue will be learing how the api works.
- **Market:** Large; We have a large market since the app would provide significant value to any group of anime enjoyers.

## Product Spec

### 1. User Features (Required and Optional)

Required Features:

- **User searches for an anime by title.**
=> Display search results with key details (title, genres, rating).
- **User selects an anime from the list.**
=> Display anime details (synopsis, episode list, trailers).
- **User explores anime by category (e.g., Shonen).**
=> Display anime within the selected category, sorted by popularity.

Stretch Features:

- **User takes a personality quiz.**
=> Generate anime recommendations based on quiz results.
- **User views a personalized list made by the Dev team**
=> Save and share the list with other users.

### 2. Chosen API(s)

- **https://api.jikan.moe/v4/anime/{id}/full**
    - Fetches comprehensive anime details.
- **https://api.jikan.moe/v4/anime/{id}**
    - Fetches basic anime information for faster loading.
  

### 3. User Interaction

Required Feature

- User searches for an anime by title.
    => Display search results with key details (title, genres, rating).
- User selects an anime from the list.
    => Display anime details (synopsis, episode list,         trailers).
- User explores anime by category (e.g., Shonen).
    => Display anime within the selected category, sorted     by popularity.

## Wireframes

**Home Screen**
    - Categories like Shonen, Romance, and Adventure.
    - Search bar at the top for quick access.
**Search Results Screen**
    - A grid view of anime with cover art, title, and rating.
**Details Screen**
    - Cover image, synopsis, genres.
**Community Screen (Stretch)**
    - User-generated lists and trending recommendations.

![Mockup.drawio](https://hackmd.io/_uploads/HJgxsu5zJl.png)



## Build Notes

Here's a place for any other notes on the app, it's creation 
process, or what you learned this unit!  

For Milestone 2, include **2+ Videos/GIFs** of the build process here!

## License

Copyright **2024** **AndriodDev-Team**

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
