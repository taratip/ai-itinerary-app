import { useNavigate } from "react-router-dom";

const HomePage = () => {
  const navigate = useNavigate();

  return (
    <div className="min-h-screen flex flex-col items-center justify-center px-4 bg-gradient-to-br from-blue-100 to-white">
      <h1 className="text-4xl sm:text-5xl font-bold mb-4 text-center text-gray-800">
        ✈️ AI-Powered Travel Itinerary Generator
      </h1>
      <p className="text-lg text-gray-600 text-center max-w-xl mb-8">
        Plan your perfect trip with just a few inputs — let AI build your itinerary based on destination, budget, and duration.
      </p>
      <button
        onClick={() => navigate("/create")}
        className="px-6 py-3 text-lg bg-blue-600 text-white rounded-xl shadow hover:bg-blue-700 transition"
      >
        Start Planning
      </button>
    </div>
  );
}

export default HomePage;
