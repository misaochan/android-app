# android-app
This is an Android app that enables users to access the information from Auckland University's non-responsive computer science website in a manner that fits the UI of mobile devices, as well as provides additional mobile-specific functionality.

It collects information from the web service at http://redsox.tcs.auckland.ac.nz/734A/CSService.svc which outputs the data as JSON objects. From this data source, it obtains a list of courses offered, as well as staff details and contacts. Additional mobile-specific functionality is included:
* Adding the contact details from the vCard to the phone's Address Book.
* If an email is selected, the Mail app is fired to compose an email to the selected address.
* If a phone number is selected and if the device is capable of making an outgoing call, an outgoing call is initiated with the permission of the user.

The app also accesses the university's RSS feeds and displays a list of active seminars, active events, and current news items. 

Update 12 Sep 15: The web service that this app relies on is no longer functional.
