import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axiosInstance from "../configurations/Appconstants";

const ExamPortal = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { questions = [] } = location.state || {};
  const userEmail = sessionStorage.getItem('userEmail') || '';
  
  const [currentIndex, setCurrentIndex] = useState(0);
  const [answers, setAnswers] = useState(Array(questions.length).fill(0));
  const [showTimeModule, setShowTimeModule] = useState(false);
  const [timeDetails, setTimeDetails] = useState({
    timeInWeeks: '',
    dailyTime: ''
  });

  // Update confidence level
  const updateConfidence = (val) => {
    const updated = [...answers];
    updated[currentIndex] = val;
    setAnswers(updated);
  };

  // Navigation handlers
  const handleNext = () => {
    if (currentIndex < questions.length - 1) setCurrentIndex(prev => prev + 1);
  };

  const handlePrevious = () => {
    if (currentIndex > 0) setCurrentIndex(prev => prev - 1);
  };

  // Submit confidence levels
  const handleSubmitConfidence = () => {
    setShowTimeModule(true);
  };

  // Handle time details change
  const handleTimeChange = (e) => {
    const { name, value } = e.target;
    setTimeDetails(prev => ({
      ...prev,
      [name]: value
    }));
  };

  // Final submission
  const handleFinalSubmit = () => {
    // Format the response as Map<String, Integer>
    const responseMap = {};
    questions.forEach((question, index) => {
      responseMap[question] = answers[index];
    });

    // Prepare the final data object
    const submissionData = {
      email: userEmail,
      response: responseMap,
      timeInWeeks: parseInt(timeDetails.timeInWeeks),
      dailyTime: parseInt(timeDetails.dailyTime)
    };

    console.log("Final Submission Data:", submissionData);

    axiosInstance.post("/send-response", submissionData)
  .then(response => {
    alert("Assessment submitted successfully!");
    navigate("/");
  })
  .catch(error => {
    console.error("Submission error:", error);
    alert("Failed to submit assessment. Please try again.");
  });
  };

  // Time Commitment Module
  if (showTimeModule) {
    return (
      <div className="flex justify-center items-center min-h-screen bg-gray-100 p-4">
        <div className="bg-white rounded-lg shadow-md p-6 w-full max-w-md">
          <h2 className="text-xl font-bold mb-6 text-center">Study Duration</h2>
          
          <div className="space-y-4">
            <div>
              <label className="block mb-2 font-medium">Total weeks needed:</label>
              <input 
                type="number"
                name="timeInWeeks"
                min="1"
                value={timeDetails.timeInWeeks}
                onChange={handleTimeChange}
                className="w-full p-2 border rounded focus:ring-2 focus:ring-blue-500"
                placeholder="Enter weeks"
                required
              />
            </div>
            
            <div>
              <label className="block mb-2 font-medium">Daily study time (hours):</label>
              <input 
                type="number"
                name="dailyTime"
                min="1"
                max="24"
                value={timeDetails.dailyTime}
                onChange={handleTimeChange}
                className="w-full p-2 border rounded focus:ring-2 focus:ring-blue-500"
                placeholder="Enter hours per day"
                required
              />
            </div>
            
            <button 
              onClick={handleFinalSubmit}
              className="w-full mt-6 bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition-colors"
            >
              Submit Assessment
            </button>
          </div>
        </div>
      </div>
    );
  }

  // Main Exam Interface
  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-100 p-4">
      <div className="bg-white rounded-lg shadow-md w-full max-w-2xl">
        {/* Header */}
        <div className="bg-blue-600 text-white p-4 rounded-t-lg">
          <div className="flex justify-between items-center">
            <h1 className="text-xl font-bold">DSA Confidence Assessment</h1>
            <div className="bg-blue-700 px-3 py-1 rounded">
              {currentIndex + 1}/{questions.length}
            </div>
          </div>
        </div>
        
        {/* Question */}
        <div className="p-6 border-b">
          <p className="text-lg font-medium">{questions[currentIndex]}</p>
        </div>
        
        {/* Confidence Selector */}
        <div className="p-6">
          <div className="flex justify-between mb-6">
            {[0, 1, 2, 3, 4].map((level) => (
              <button
                key={level}
                onClick={() => updateConfidence(level)}
                className={`w-12 h-12 rounded-full flex items-center justify-center
                  ${answers[currentIndex] === level 
                    ? 'bg-blue-600 text-white' 
                    : 'bg-gray-200 hover:bg-gray-300'}`}
              >
                {level}
              </button>
            ))}
          </div>
          
          <div className="flex justify-between mt-8">
            <button
              onClick={handlePrevious}
              disabled={currentIndex === 0}
              className={`px-4 py-2 rounded
                ${currentIndex === 0 
                  ? 'bg-gray-300 cursor-not-allowed' 
                  : 'bg-blue-600 text-white hover:bg-blue-700'}`}
            >
              Previous
            </button>
            
            {currentIndex === questions.length - 1 ? (
              <button
                onClick={handleSubmitConfidence}
                className="px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700"
              >
                Submit Confidence Levels
              </button>
            ) : (
              <button
                onClick={handleNext}
                className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
              >
                Next
              </button>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default ExamPortal;