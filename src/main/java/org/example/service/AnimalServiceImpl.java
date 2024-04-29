package org.example.service;

import org.example.Animal;
import org.example.repository.AnimalRepository;
import org.example.repository.AnimalRepositoryImpl;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService{
    private final AnimalRepository animalRepository;
    private Animal animal;


    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public Animal findById(Long id) {
        return animalRepository.findById(id);
    }

    @Override
    public void saveAnimal(Animal animal) {
        animalRepository.save(animal);
    }
    @Override
    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }
    @Override
    public void processCsvFile(InputStream inputStream) { }
    @Override
    public void processXmlFile(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            // Создание фабрики для получения парсера XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Разбор XML из InputStream
            Document document = builder.parse(inputStream);
            // Получение корневого элемента
            Element root = document.getDocumentElement();
            // Получение всех элементов <animal>
            NodeList animalList = root.getElementsByTagName("animal");
            // Обработка каждого элемента <animal>
            for (int i = 0; i < animalList.getLength(); i++) {
                Node animalNode = animalList.item(i);
                if (animalNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element animalElement = (Element) animalNode;
                    // Получение значений элементов внутри <animal>
                    String name = getElementValues(animalElement, "name").toString();
                    String type = getElementValues(animalElement, "type").toString();
                    String sex = getElementValues(animalElement, "sex").toString();
                    int weight = Integer.parseInt(getElementValues(animalElement, "weight").toString());
                    double cost = Double.parseDouble(getElementValues(animalElement, "cost").toString());


                }
            }

        } catch (Exception e) {
        }


    }
    private List<String> getElementValues(Element parentElement, String elementName) {
        List<String> values = new ArrayList<>();
        NodeList nodeList = parentElement.getElementsByTagName(elementName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                values.add(node.getTextContent().trim());
            }
        }
        return values;
    }
}
