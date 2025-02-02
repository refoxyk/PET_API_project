from flask import Flask
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.config['JSON_SORT_KEYS'] = False
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://main:k!gcsgZw7@localhost/fitnes_app_database'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)


class Klients(db.Model):
    __tablename__ = 'klients'
    klientID = db.Column(db.Integer, primary_key=True, autoincrement=True)
    fullName = db.Column(db.String(255), nullable=False)
    email = db.Column(db.String(255), unique=True, nullable=False) 
    phoneNumber = db.Column(db.String(20), unique=True, nullable=False) 
    address = db.Column(db.Text, nullable=True)
    createdAt = db.Column(db.DateTime, server_default=db.func.now())

  
    orders = db.relationship('Pasutijums', back_populates='klient', cascade='all, delete-orphan')


class Kurjers(db.Model):
    __tablename__ = 'kurjers'
    kurjerID = db.Column(db.Integer, primary_key=True, autoincrement=True)
    fullName = db.Column(db.String(255), nullable=False)
    email = db.Column(db.String(255), unique=True, nullable=False)  
    phoneNumber = db.Column(db.String(20), unique=True, nullable=False)  
    batteryLevel = db.Column(db.Float, nullable=False) 
    available = db.Column(db.Boolean, default=True, nullable=False)  
    locationLatitude = db.Column(db.Float, nullable=False)  
    locationLongitude = db.Column(db.Float, nullable=False)  
    lastUpdate = db.Column(db.DateTime, server_default=db.func.now(), onupdate=db.func.now()) 

    orders = db.relationship('Pasutijums', back_populates='kurjer', cascade='all, delete-orphan')


class Pasutijums(db.Model):
    __tablename__ = 'pasutijums'
    orderID = db.Column(db.Integer, primary_key=True, autoincrement=True)
    klientID = db.Column(db.Integer, db.ForeignKey('klients.klientID'), nullable=False) 
    kurjerID = db.Column(db.Integer, db.ForeignKey('kurjers.kurjerID'), nullable=True) 
    deliveryAddress = db.Column(db.Text, nullable=False) 
    deliveryFee = db.Column(db.Float, nullable=False)  
    status = db.Column(
        db.Enum('Pending', 'In Progress', 'Completed', 'Cancelled'),
        default='Pending',
        nullable=False
    ) 
    createdAt = db.Column(db.DateTime, server_default=db.func.now())  
    updatedAt = db.Column(db.DateTime, server_default=db.func.now(), onupdate=db.func.now()) 


    klient = db.relationship('Klients', back_populates='orders')
   
    kurjer = db.relationship('Kurjers', back_populates='orders')



with app.app_context():
    db.create_all()
