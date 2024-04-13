import requests
import json




prompts_quests = {"Output a numbered 1 - 5 comma delimited list of exercises(30 Bicep Curls, 30 Pushups, 2 Miles of Walking, and 2 Minutes of stretching) in the flavor of trials and tests the hero must endure to prove their worthiness or overcome obstacles. These could be physical, mental, or emotional challenges.", "Output a numbered 1 - 5 comma delimited list of exercises(30 Bicep Curls, 30 Pushups, 2 Miles of Walking, and 2 Minutes of stretching) in the flavor of Explore the conflicts or adversaries the hero must face, whether internal or external. What fears, doubts, or enemies must they confront and overcome?", "Output a numbered 1 - 5 comma delimited list of exercises(30 Bicep Curls, 30 Pushups, 2 Miles of Walking, and 2 Minutes of stretching) in the flavor of  Explore the conflicts or adversaries the hero must face, whether internal or external. What fears, doubts, or enemies must they confront and overcome?", "Output a numbered 1 - 5 comma delimited list of exercises(30 Bicep Curls, 30 Pushups, 2 Miles of Walking, and 2 Minutes of stretching) in the flavor of finding an artifact for them to bring back home as spoils"}

def query_API(API_KEY, AI_Prompt):
    ANTHROPIC_API_KEY = API_KEY
    API_URL = 'https://api.anthropic.com/v1/messages'

    headers = {
        'x-api-key': ANTHROPIC_API_KEY,
        'anthropic-version': '2023-06-01',
        'content-type': 'application/json',
    }

    data = {
        "model": "claude-3-opus-20240229",
        "max_tokens": 1024,
        "messages": [
            {"role": "user", "content": AI_Prompt}
        ]
    }

    response = requests.post(API_URL, headers=headers, json=data)
    
    if response.status_code == 200:
        return extract_text(response.json())
    else:
        return "Error:", response.status_code, response.text

def query_story_API(API_KEY):
    ANTHROPIC_API_KEY = API_KEY
    API_URL = 'https://api.anthropic.com/v1/messages'

    headers = {
        'x-api-key': ANTHROPIC_API_KEY,
        'anthropic-version': '2023-06-01',
        'content-type': 'application/json',
    }

    data = {
        "model": "claude-3-opus-20240229",
        "max_tokens": 2000,
        "temperature": 1,
        "system": "You are an AI assistant acting as a wise wizard your job is to engage the user into following a fitness plan by framing it as training and an epic quest",
        "messages": [
            {"role": "user",
            "content": "Let's create a brief (5 lines at most) yet engaging introduction as our hero (the user) is about to embark on a journey of fitness to for both power and fun"}
        ]
    }

    response = requests.post(API_URL, headers=headers, json=data)

    if response.status_code == 200:
        return extract_text(response.json())
    else:
        return "Error:", response.status_code, response.text

def query_fitness_API(API_KEY):
    ANTHROPIC_API_KEY = API_KEY
    API_URL = 'https://api.anthropic.com/v1/messages'

    headers = {
        'x-api-key': ANTHROPIC_API_KEY,
        'anthropic-version': '2023-06-01',
        'content-type': 'application/json',
    }

    data = {
        "model": "claude-3-opus-20240229",
        "max_tokens": 2000,
        "temperature": 1,
        "system": "You are an AI assistant acting like a wizard, your job is to add brief one liner flavor texts to exercises (for example do 10 curls to train your sword arm)",
        "messages": [
            {"role": "user",
            "content": "Output a numbered 1 - 5 comma delimited list of exercises(30 Bicep Curls, 30 Pushups, 2 Miles of Walking, and 2 Minutes of stretching) in a mix of flavor of the following prompts Monster Hunt: Describe a fierce creature that the hero must track down and defeat to progress on their quest. What unique abilities or weaknesses does the monster possess? Guardian Duel: Detail a confrontation between the hero and a powerful guardian who protects a sacred artifact or passage. How does the hero outsmart or overpower their opponent? Rescue Mission: Create a scenario where the hero must rescue a group of captives held by an evil force. What obstacles must they overcome to free the prisoners and ensure their escape to safety? Ancient Ruins Exploration: Send the hero into ancient ruins or a forbidden temple in search of a hidden treasure or lost knowledge. What traps, puzzles, or guardians block their path? Wilderness Survival: Describe a journey through treacherous wilderness where the hero must navigate harsh terrain, evade dangerous creatures, and find sustenance to continue their quest. Underworld Descent: Task the hero with descending into the underworld or a dark realm to retrieve a vital artifact or rescue a lost soul. What horrors and challenges await them in the depths? Elemental Trials: Present a series of elemental challenges (earth, fire, water, air) that the hero must overcome to prove their mastery and gain access to a hidden sanctuary or power source. Legendary Beast Taming: Introduce a legendary creature that the hero must tame or ally with to gain its aid in their quest. How does the hero earn the creature's trust and loyalty? Cursed Relic Purification: Describe a cursed relic or artifact that the hero must purify or destroy to prevent it from falling into the wrong hands. What sacrifices or rituals are required to cleanse the object?"}
        ]
    }

    response = requests.post(API_URL, headers=headers, json=data)

    if response.status_code == 200:
        return extract_text(response.json())
    else:
        return "Error:", response.status_code, response.text

def extract_text(json_string):
    # Parse the JSON string into a Python dictionary
    json_dict = json.loads(json.dumps(json_string))
    # Extract the 'content' list
    content_list = json_dict.get('content', [])
    # Extract the 'text' field from the first item in the 'content' list
    text = content_list[0].get('text', '') if content_list else ''

    return text




API_KEY = "Something as of right now IDK what it is exactly"


index = 0

print(extract_text(query_API(API_KEY, prompts[index])))