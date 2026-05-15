import streamlit as st
import joblib
import re
import nltk
from nltk.corpus import stopwords

# Page configuration
st.set_page_config(
    page_title="IMDb Sentiment Analyzer",
    page_icon="🎬",
    layout="centered"
)

# Custom CSS for a premium look
st.markdown("""
    <style>
    .main {
        background-color: #0e1117;
    }
    .stTextArea textarea {
        background-color: #161b22;
        color: #c9d1d9;
        border: 1px solid #30363d;
        border-radius: 10px;
    }
    .stButton>button {
        width: 100%;
        border-radius: 10px;
        background: linear-gradient(90deg, #1f6feb 0%, #238636 100%);
        color: white;
        border: none;
        padding: 10px;
        font-weight: bold;
    }
    .sentiment-box {
        padding: 20px;
        border-radius: 15px;
        text-align: center;
        margin-top: 20px;
    }
    .positive {
        background-color: rgba(35, 134, 54, 0.2);
        border: 1px solid #238636;
        color: #3fb950;
    }
    .negative {
        background-color: rgba(248, 81, 73, 0.2);
        border: 1px solid #f85149;
        color: #f85149;
    }
    </style>
""", unsafe_allow_html=True)

# Load resources
@st.cache_resource
def load_assets():
    model = joblib.load('sentiment_model.pkl')
    vectorizer = joblib.load('tfidf_vectorizer.pkl')
    nltk.download('stopwords', quiet=True)
    return model, vectorizer

def clean_text(text):
    text = re.sub(r'<.*?>', '', text)
    text = re.sub(r'[^a-zA-Z]', ' ', text)
    text = text.lower()
    return ' '.join(text.split())

# App UI
st.title("🎬 IMDb Sentiment Analyzer")
st.markdown("Analyze the sentiment of movie reviews using Machine Learning.")

try:
    model, vectorizer = load_assets()
    
    review_input = st.text_area("Paste a movie review here:", height=200, placeholder="The cinematography was breathtaking and the acting was top-notch...")

    if st.button("Analyze Sentiment"):
        if review_input.strip() != "":
            cleaned_review = clean_text(review_input)
            review_vector = vectorizer.transform([cleaned_review])
            prediction = model.predict(review_vector)[0]
            probability = model.predict_proba(review_vector)[0]
            
            confidence = probability[1] if prediction == 1 else probability[0]
            sentiment = "POSITIVE" if prediction == 1 else "NEGATIVE"
            style_class = "positive" if prediction == 1 else "negative"
            icon = "😊" if prediction == 1 else "😞"
            
            st.markdown(f"""
                <div class="sentiment-box {style_class}">
                    <h2>{icon} {sentiment}</h2>
                    <p>Confidence: {confidence:.2%}</p>
                </div>
            """, unsafe_allow_html=True)
        else:
            st.warning("Please enter some text first!")

except FileNotFoundError:
    st.error("Model files not found! Please run the training notebook (`Ca2.ipynb`) first to generate `sentiment_model.pkl` and `tfidf_vectorizer.pkl`.")
except Exception as e:
    st.error(f"An error occurred: {e}")

st.sidebar.title("About")
st.sidebar.info("This app uses a Logistic Regression model trained on 50,000 IMDb reviews to predict sentiment.")
st.sidebar.markdown("---")
st.sidebar.markdown("Built with ❤️ using Streamlit & Scikit-learn")
