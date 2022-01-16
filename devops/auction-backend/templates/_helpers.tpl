{{/*
Expand the name of the chart.
*/}}
{{- define "auction-backend.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "auction-backend.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Annotations for ingress
*/}}
{{- define "auction-backend.annotations" -}}
kubernetes.io/ingress.class: nginx
cert-manager.io/cluster-issuer: "letsencrypt-staging"
{{- end }}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "auction-backend.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "auction-backend.labels" -}}
helm.sh/chart: {{ include "auction-backend.chart" . }}
{{ include "auction-backend.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
app.kubernetes.io/name: {{ include "auction-backend.name" . }}
app.kubernetes.io/instance: backend
app.kubernetes.io/component: backend
app.kubernetes.io/part-of: app
app.kubernetes.io/created-by: helm
{{- end }}

{{/*
Selector labels
*/}}
{{- define "auction-backend.selectorLabels" -}}
app.kubernetes.io/name: {{ include "auction-backend.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "auction-backend.serviceAccountName" -}}
{{- if .Values.serviceAccount.create }}
{{- default (include "auction-backend.fullname" .) .Values.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.serviceAccount.name }}
{{- end }}
{{- end }}
