# Prometheus

## What is Prometheus?

Prometheus is an open-source systems monitoring and
alerting toolkit originally built at SoundCloud. 
Since its inception in 2012, many companies and 
organizations have adopted Prometheus, and the 
project has a very active developer and user
community. It is now a standalone open source 
project and maintained independently of any company.
To emphasize this, and to clarify the project's 
governance structure, Prometheus joined the Cloud 
Native Computing Foundation in 2016 as the second 
hosted project, after Kubernetes.

Prometheus collects and stores its metrics as time 
series data, i.e. metrics information is stored with
the timestamp at which it was recorded, alongside 
optional key-value pairs called labels.

## What are metrics ?
In layperson terms, metrics are numeric measurements.
Time series means that changes are recorded over 
time. What users want to measure differs from 
application to application. For a web server it 
might be request times, for a database it might be
number of active connections or number of active
queries etc.

Metrics play an important role in understanding why
your application is working in a certain way. Let's
assume you are running a web application and find 
that the application is slow. You will need some 
information to find out what is happening with your
application. For example the application can become
slow when the number of requests are high. If you 
have the request count metric you can spot the
reason and increase the number of servers to handle
the load.

## Components
The Prometheus ecosystem consists of multiple 
components, many of which are optional:
the main Prometheus server which scrapes and stores time series data
* client libraries for instrumenting application code
* a push gateway for supporting short-lived jobs
* special-purpose exporters for services like HAProxy, StatsD, Graphite, etc.
* an alertmanager to handle alerts
* various support tools

Most Prometheus components are written in Go, making
them easy to build and deploy as static binaries.

## Architecture
This diagram illustrates the architecture of
Prometheus and some of its ecosystem components:

![](img/prometheus-architecture.png)

Prometheus scrapes metrics from instrumented jobs, 
either directly or via an intermediary push gateway
for short-lived jobs. It stores all scraped samples
locally and runs rules over this data to either
aggregate and record new time series from existing
data or generate alerts. Grafana or other API 
consumers can be used to visualize the collected 
data.

## References

* https://prometheus.io/
