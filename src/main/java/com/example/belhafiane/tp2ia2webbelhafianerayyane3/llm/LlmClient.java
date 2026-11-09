package com.example.belhafiane.tp2ia2webbelhafianerayyane3.llm;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import io.github.cdimascio.dotenv.Dotenv;

import jakarta.enterprise.context.Dependent;
import java.io.Serializable;

@Dependent
public class LlmClient implements Serializable {

    private static final long serialVersionUID = 1L;

    private String systemRole;

    private Assistant assistant;

    private ChatMemory chatMemory;

    private String llmKey;

    public LlmClient() {

        Dotenv dotenv = Dotenv.load();
        this.llmKey = dotenv.get("GEMINI_KEY");

        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(llmKey)
                .modelName("gemini-3.5-flash-lite")
                .temperature(0.3)
                .build();

        this.chatMemory = MessageWindowChatMemory.withMaxMessages(20);

        this.assistant = AiServices.builder(Assistant.class)
                .chatModel(model)
                .chatMemory(chatMemory)
                .build();
    }

    public void setSystemRole(String systemRole) {

        this.systemRole = systemRole;

        chatMemory.clear();

        chatMemory.add(SystemMessage.from(systemRole));
    }

    public String chat(String prompt) {

        return assistant.chat(prompt);
    }
}
