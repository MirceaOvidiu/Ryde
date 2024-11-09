import React, { useState } from 'react';
import Login from './Login';
import UserRegistration from './UserRegistration';
import './App.css';

function App() {
    const [showLogin, setShowLogin] = useState(true);

    return (
        <div className="App">
            <header className="App-header">
                <h1>Welcome</h1>
                <div className="button-container">
                    <button onClick={() => setShowLogin(true)} className="btn">User Login</button>
                    <button onClick={() => setShowLogin(false)} className="btn">User Registration</button>
                </div>
                {showLogin ? <Login /> : <UserRegistration />}
            </header>
        </div>
    );
}

export default App;
