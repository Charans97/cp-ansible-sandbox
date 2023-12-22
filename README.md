kind create cluster

kubectl create ns gr

curl -O https://confluent-for-kubernetes.s3-us-west-1.amazonaws.com/confluent-for-kubernetes-2.6.0.tar.gz
tar -zxvf confluent-for-kubernetes-2.6.0.tar.gz
inside helm dir
helm upgrade --install confluent-operator ./confluent-for-kubernetes --namespace gr

kubectl apply -f kafka.yaml 

Install Prometheus
helm repo add stable https://charts.helm.sh/stable
helm repo add grafana https://grafana.github.io/helm-charts

helm repo update

helm upgrade --install demo-test stable/prometheus \
 --set alertmanager.persistentVolume.enabled=false \
 --set server.persistentVolume.enabled=false \
 --namespace gr
Install Grafana
helm upgrade --install grafana grafana/grafana --namespace gr
Open Grafana in your Browser
Start port-forwarding, so you can access Grafana in your browser with a localhost address:

 kubectl port-forward \
  $(kubectl get pods -n default -l app.kubernetes.io/name=grafana,app.kubernetes.io/instance=grafana -o name) \
  3000 --namespace gr
Get your 'admin' user password:

kubectl get secret --namespace default grafana -o jsonpath="{.data.admin-password}" | base64 --decode
Visit http://localhost:3000 in your browser, and login as the admin user with the decoded password.

Configure Grafana with a Prometheus Data Source
Follow the in-browser instructions to configure a Prometheus data source for Grafana, or consult the online documentation. You will be asked to provide a URL. Enter the URL as shown below:

http://demo-test-prometheus-server.default.svc.cluster.local
Click "Save & Test". You should see a green alert at the bottom of the page saying "Data source is working".

Import Grafana Dashboard Configuration
