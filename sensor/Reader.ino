#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

// Update HOST URL here

#define HOST "carparking1.online/"          // Enter HOST URL without "http:// "  and "/" at the end of URL

#define WIFI_SSID "network"            // WIFI SSID here                                   
#define WIFI_PASSWORD "123456789"        // WIFI password here

// Declare global variables which will be uploaded to server

int parking1 = 0;
int parking2 = 0;
int parking3 = 0;
int parking4 = 0;
int parking5 = 0;
int parking6 = 0;

int p1_status = 0;
int p2_status = 0;
int p3_status = 0;
int p4_status = 0;
int p5_status = 0;
int p6_status = 0;


String sendparking1, sendparking2, sendparking3, sendparking4, sendparking5, sendparking6, postData;


void setup() {

  pinMode(5, INPUT);  // D1  = 5
  pinMode(4, INPUT);  // D2  = 4
  pinMode(14, INPUT); // D5  = 14
  pinMode(12, INPUT); // D6  = 12
  pinMode(13, INPUT); // D7  = 13
  pinMode(16, INPUT); // D0  = 16

  Serial.begin(115200);
  Serial.println("Communication Started \n\n");
  delay(1000);


  pinMode(LED_BUILTIN, OUTPUT);     // initialize built in led on the board



  WiFi.mode(WIFI_STA);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);                                     //try to connect with wifi
  Serial.print("Connecting to ");
  Serial.print(WIFI_SSID);
  while (WiFi.status() != WL_CONNECTED)
  { Serial.print(".");
    delay(500);
  }

  Serial.println();
  Serial.print("Connected to ");
  Serial.println(WIFI_SSID);
  Serial.print("IP Address is : ");
  Serial.println(WiFi.localIP());    //print local IP address

  delay(30);
}



void loop() {

  HTTPClient http;    // http object of clas HTTPClient

  parking1 = digitalRead(5);    // D1  = 5
  parking2 = digitalRead(4);    // D2  = 4
  parking3 = digitalRead(14);   // D5  = 14
  parking4 = digitalRead(12);   // D6  = 12
  parking5 = digitalRead(13);   // D7  = 13
  parking6 = digitalRead(16);   // D0  = 16


  if (parking1 == 0) {
    parking1 = 2;
  }
  if (parking2 == 0) {
    parking2 = 2;
  }
  if (parking3 == 0) {
    parking3 = 2;
  }
  if (parking4 == 0) {
    parking4 = 2;
  }
  if (parking5 == 0) {
    parking5 = 2;
  }
  if (parking6 == 0) {
    parking6 = 2;
  }

  if (p1_status != parking1 || p2_status != parking2 || p3_status != parking3 || p4_status != parking4 || p5_status != parking5 || p6_status != parking6)
  {
    
    p1_status = parking1;
    p2_status = parking2;
    p3_status = parking3;
    p4_status = parking4;
    p5_status = parking5;
    p6_status = parking6;

    // Convert integer variables to string
    sendparking1 = String(parking1);
    sendparking2 = String(parking2);
    sendparking3 = String(parking3);
    sendparking4 = String(parking4);
    sendparking5 = String(parking5);
    sendparking6 = String(parking6);


    postData = "sendparking1=" + sendparking1 + "&sendparking2=" + sendparking2 + "&sendparking3=" + sendparking3 + "&sendparking4=" + sendparking4 + "&sendparking5=" + sendparking5 + "&sendparking6=" + sendparking6;


    http.begin("http://carparking1.online/dbwrite.php");              // Connect to host where MySQL databse is hosted
    http.addHeader("Content-Type", "application/x-www-form-urlencoded");            //Specify content-type header



    int httpCode = http.POST(postData);   // Send POST request to php file and store server response code in variable named httpCode
    Serial.println("Values are, sp1 = " + sendparking1 + " and sp2 = " + sendparking2 + " and sp3 = " + sendparking3 + " and sp4 = " + sendparking4 + " and sp5 = " + sendparking5 + " and sp6 = " + sendparking6 );


    // if connection eatablished then do this
    if (httpCode == 200) {
      Serial.println("Values uploaded successfully."); Serial.println(httpCode);
      String webpage = http.getString();    // Get html webpage output and store it in a string
      Serial.println(webpage + "\n");
    }

    // if failed to connect then return and restart

    else {
      Serial.println(httpCode);
      Serial.println("Failed to upload values. \n");
      http.end();
      return;
    }


    delay(3000);
    digitalWrite(LED_BUILTIN, LOW);  
    delay(3000);
    digitalWrite(LED_BUILTIN, HIGH);
  }
}// end of loop
