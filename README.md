# HarvestHaven
RisaApp is a mobile application that avails farm products to customers

#auth
The app allows users to sign up and log in using Firebase Authentication
After signing up or logging in, users land on the dashboard screen

#navigation
From the dashboard, users can navigate to the product list screen
The product list screen displays a variety of farm products such as tomatoes, sukuma wiki etc


Users can add products to their cart or contact the farmer directly from the product list
A button on the product list screen shows the current number of items in the cart and allows navigation to the cart screen
The cart screen shows all added items and allows users to remove items or clear the entire cart


Users can checkout from the cart screen, which resets the cart and returns them to the dashboard

The logout option is available on the product list screen to securely sign the user out



Firebase is used for  user authentication
Hilt is used for dependency injection where needed
All data such as products is currently hardcoded for demonstration purposes, with literally no  API connection
