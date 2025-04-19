import React, { useState } from "react";

const ExamPortal = () => {
  const question = [
    "What is JVM?",
    "What is the use of JRE?",
    "Explain encapsulation.",
    "What are the four pillars of OOP?",
  ];
  const lengths = question.length;

  const [currentIndex, setCurrentIndex] = useState(0);
  const [value, setValue] = useState(1);

  // Handlers
  const handleNext = () => {
    if (currentIndex < lengths - 1) setCurrentIndex((prev) => prev + 1);
  };

  const handlePrevious = () => {
    if (currentIndex > 0) setCurrentIndex((prev) => prev - 1);
  };

  return (
    <div className="flex justify-center items-center bg-black w-screen h-screen">
      <div className="w-screen h-screen sm:w-[80%] sm:h-[80%] m-auto bg-white rounded-lg flex flex-col justify-around gap-3 p-5">
        {/* Question Section */}
        <div className="min-h-[300px] bg-yellow-500 sm:h-2/3 flex justify-center items-center relative">
          <div className="absolute top-10 right-10 font-semibold text-white">
            Question: {currentIndex + 1} / {lengths}
          </div>
          <p className="text-xl font-medium text-center px-4">
            {question[currentIndex]}
          </p>
          <button
            onClick={() => alert("Test Finished!")}
            disabled={currentIndex !== lengths - 1}
            className={`p-3 absolute right-10 bottom-10 rounded-md text-white ml-3 bg-red-600 transition-opacity duration-500 ${
              currentIndex === lengths - 1
                ? "opacity-100"
                : "opacity-0 pointer-events-none"
            }`}
          >
            FINISH TEST
          </button>
        </div>

        {/* Confidence Selector */}
        <div className="bg-pink-600 h-1/3 flex flex-col items-center justify-center gap-4">
          <p className="text-white text-lg">Confidence level: {value}</p>

          <div className="flex items-center relative justify-between gap-x-6 w-full max-w-sm">
            {Array.from({ length: 5 }, (_, i) => (
              <div
                key={i}
                onClick={() => setValue(i)}
                className={`w-8 h-8 sm:w-10 sm:h-10 cursor-pointer flex items-start justify-center rounded-full z-10 
                    transition-transform duration-300 p-1 ${
                      i <= value ? "bg-green-500" : "bg-gray-300"
                    }
                  `}
              >
                {i <= value ? (
                  <img src="src/Images/coloredBrain.png" alt="colored" />
                ) : (
                  <img src="src/Images/grayBrain.png" alt="gray" />
                )}
              </div>
            ))}

            {/* Line Behind */}
            <div className="absolute top-1/2 left-0 w-full h-2 -translate-y-1/2 bg-gray-300 rounded-full z-0">
              <div
                className="h-full bg-green-400 rounded-full transition-all duration-300"
                style={{ width: `${(value / (5 - 1)) * 100}%` }}
              ></div>
            </div>
          </div>

          {/* Navigation Buttons */}
          <div className="w-full h-10 flex justify-between items-center">
            <button
              onClick={handlePrevious}
              disabled={currentIndex === 0}
              className={`p-3 rounded-md text-white ml-3 ${
                currentIndex === 0
                  ? "bg-gray-400 cursor-not-allowed"
                  : "bg-sky-500"
              }`}
            >
              PREVIOUS
            </button>
            <button
              onClick={handleNext}
              disabled={currentIndex === lengths - 1}
              className={`min-w-20 p-3 rounded-md text-white mr-3 ${
                currentIndex === lengths - 1
                  ? "bg-gray-400 cursor-not-allowed"
                  : "bg-sky-500"
              }`}
            >
              NEXT
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ExamPortal;
