# Vulnerability Analysis

## A10:2017 Insufficient Logging & Monitoring
### Description
When your application is not being logged by itself or being monitored during for example transactions,
it can lead to suspicious activity.

In this context it could mean that thousands of api requests can be made and you can't be sure where it comes from.
The application is unable to detect, escalate, or alert for active attacks in real time or near real time.

###Risk
This is a risk within our application. There is no authentication or authorization required to perform API calls.
This would mean an attacker could perform thousands of calls and we can't be sure where it's coming from or slow down the API calls.
This could result in an DDOS-attack.

###Counter-measures taken
None as of yet.

However, counter-measures would consist making logs of when API-calls are made, and or adding user authentication to slow
down the calls. Effective monitoring tools can be deployed to alert us when suspicious activities are made.



## A9:2017 Using Components with Known Vulnerabilities

### Description
If attackers have knowledge over security or vulnerability issues within a component, think Frameworks and libraries,
and you use those. Attackers could exploit a weakness and can reign destruction upon your application.
### Risk
In this application there could be little risk, as it is a fairly simple application with no user authentication or authorization.
However, were we using those, there would be the risk that the attackers could for example execute code on the application server.

### Counter-measures taken
Dependabot is added to the repository for version checking all dependencies.
No unknown frameworks are used.

## A1:2017 - Injection
### Description
Injection means that attackers could execute their own code, mostly SQL code on a database, if done correctly by the 
attackers, this could mean they could ask for the entire database, which could contain User tables and passwords. Or worse,
they could drop your entire database.
### Risk
It is possible in this context to fill in your guess during lingo, attackers might be able to perform their own code during
the api request.
### Counter-measures taken
We are not building statements with strings and executing those.