FROM python:3.13

WORKDIR /app

COPY python.py .

CMD ["python","python.py"]
