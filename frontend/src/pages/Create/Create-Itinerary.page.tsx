import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Create-Itinerary.style.scss"

const CreateItineraryPage = () => {
  const navigate = useNavigate();

  const [destination, setDestination] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [budget, setBudget] = useState("");
  const [interests, setInterests] = useState<string[]>([]);

  const allInterests = ["Food", "Culture", "Nature", "Adventure", "Relaxation"];

  const handleDestinationChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setDestination(e.target.value);
  }

  const handleStartDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setStartDate(e.target.value);
  }

  const handleEndDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEndDate(e.target.value);
  }

  const handleBudgetChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setBudget(e.target.value);
  }

  const toggleInterest = (interest: string) => {
    setInterests((prev) =>
      prev.includes(interest) ? prev.filter((i) => i !== interest) : [...prev, interest]
    );
  }

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    // TODO: Send to backend for AI generation
    console.log({ destination, startDate, endDate, budget, interests });

    navigate("/itinerary/123"); // placeholder until backend returns ID
  };

  return (
    <div className="create-itinerary-container">
      <h2 className="create-itinerary-title">Create Your Trip</h2>
      <form onSubmit={handleSubmit} className="create-itinerary-form">
        <div>
          <label className="form-label">Destination</label>
          <input
            type="text"
            value={destination}
            onChange={handleDestinationChange}
            className="form-input"
            required
          />
        </div>

        <div className="form-group">
          <div className="form-group-item">
            <label className="form-label">Start Date</label>
            <input
              type="date"
              value={startDate}
              onChange={handleStartDateChange}
              className="form-input"
              required
            />
          </div>
          <div className="form-group-item">
            <label className="form-label">End Date</label>
            <input
              type="date"
              value={endDate}
              onChange={handleEndDateChange}
              className="form-input"
              required
            />
          </div>
        </div>

        <div>
          <label className="form-label">Budget ($)</label>
          <input
            type="number"
            value={budget}
            onChange={handleBudgetChange}
            className="form-input"
            required
          />
        </div>

        <div>
          <label className="create-itinerary-interests">Interests</label>
          <div className="create-itinerary-interests-container">
            {allInterests.map((interest) => (
              <button
                key={interest}
                type="button"
                className={`interest-button ${
                  interests.includes(interest)
                    ? "interest-button--selected"
                    : "interest-button--unselected"
                }`}
                onClick={() => toggleInterest(interest)}
              >
                {interest}
              </button>
            ))}
          </div>
        </div>

        <button
          type="submit"
          className="submit-button"
        >
          🪄 Generate Itinerary
        </button>
      </form>
    </div>
  );
}

export default CreateItineraryPage;
