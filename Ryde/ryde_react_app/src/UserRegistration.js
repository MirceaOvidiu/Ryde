import React, { useState } from 'react';
import axios from 'axios';
import './UserRegistration.css';

const UserRegistration = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [message, setMessage] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/register', { username, password, email });
            setMessage(response.data);
        } catch (error) {
            setMessage('Registration failed!');
        }
    };

    return (
        <div className="registration-container">
            <h2>User Registration</h2>
            <form onSubmit={handleSubmit} className="registration-form">
                <div className="form-group">
                    <label>Username:</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        className="form-control"
                        placeholder={"username"}
                    />
                </div>
                <div className="form-group">
                    <label>Password:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="form-control"
                        placeholder={"super secret password"}
                    />
                </div>
                <div className="form-group">
                    <label>Email:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="form-control"
                        placeholder={"your email address"}
                    />
                </div>
                <button type="submit" className="btn">Register</button>
            </form>
            {message && <p className="message">{message}</p>}
        </div>
    );
};

export default UserRegistration;