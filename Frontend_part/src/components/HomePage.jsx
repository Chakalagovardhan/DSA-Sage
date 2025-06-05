import axiosInstance from "../configurations/Appconstants";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const HomePage = () => {
    const [formresponse, setformresponse] = useState({
        email: "",
        exam: "Select"
    });
    const [response, setresponse] = useState(null);
    const navigate = useNavigate();

    const fromsubmit = async (e) => {
        try {
            e.preventDefault();
            sessionStorage.setItem('userEmail', formresponse.email);
            const res = await axiosInstance.get('/dsaquestions');
            setresponse(res.data);
            console.log(res.data);
            navigate("/examportal", {
                state: {
                    questions: res.data.questions,
                    timedetails: res.data.timedetails,
                }
            });
        } catch (error) {
            console.log(error);
        }
    };

    const onchange = (e) => {
        const { name, value } = e.target;
        setformresponse((prev) => ({
            ...prev,
            [name]: value
        }));
    };

    return (
        <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 flex justify-center items-center p-4">
            <div className="w-full max-w-md bg-white rounded-2xl shadow-xl overflow-hidden">
                <div className="bg-gradient-to-r from-blue-600 to-indigo-700 p-6 text-white">
                    <h1 className="text-2xl font-bold">Exam Portal</h1>
                    <p className="text-blue-100">Enter your details to begin</p>
                </div>
                
                <form onSubmit={fromsubmit} className="p-6 space-y-6">
                    <div className="space-y-2">
                        <label htmlFor="email" className="block text-sm font-medium text-gray-700">
                            Email Address
                        </label>
                        <input
                            type="email"
                            name="email"
                            id="email"
                            value={formresponse.email}
                            onChange={onchange}
                            className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all"
                            placeholder="your@email.com"
                            autoComplete="off"
                            required
                        />
                    </div>
                    
                    <div className="space-y-2">
                        <label htmlFor="exam" className="block text-sm font-medium text-gray-700">
                            Select Exam
                        </label>
                        <select
                            name="exam"
                            id="exam"
                            onChange={onchange}
                            value={formresponse.exam}
                            className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all"
                            required
                        >
                            <option value="Select" disabled>Choose an exam</option>
                            <option value="DSA">Data Structures & Algorithms</option>
                        </select>
                    </div>
                    
                    <button
                        type="submit"
                        className="w-full bg-gradient-to-r from-blue-600 to-indigo-600 hover:from-blue-700 hover:to-indigo-700 text-white font-semibold py-3 px-4 rounded-lg shadow-md hover:shadow-lg transition-all transform hover:-translate-y-0.5"
                    >
                        Start Test
                    </button>
                </form>
                
                <div className="px-6 py-4 bg-gray-50 text-center text-sm text-gray-500 border-t border-gray-200">
                    Attempt the response according to your standard
                    
                </div>
            </div>
        </div>
    );
};

export default HomePage;