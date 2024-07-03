package ch.fhnw.algd2.graphedit;

import java.util.*;
import java.util.stream.Collectors;

public final class AdjListGraph<K> extends AbstractGraph<K> implements GraphAlgorithms.DFS {

    private static class Vertex<K> {

        K data;
        List<Vertex<K>> adjList = new LinkedList<Vertex<K>>();
        boolean visited = false;

        Vertex(K vertex) {
            data = vertex;
        }

        boolean addEdgeTo(Vertex<K> to) {
            return (adjList.contains(to)) ? false : adjList.add(to);
        }
    }

    private Map<K, Vertex<K>> vertices;

    public AdjListGraph() { // default constructor
        this(false);
    }

    public AdjListGraph(boolean directed) {
        super(directed);
        vertices = new HashMap<K, Vertex<K>>();
    }

    public AdjListGraph(AdjListGraph<K> orig) { // copy constructor
        // TODO Loeschen Sie folgende Zeile und programmieren Sie
        // einen Konstruktor, der eine Kopie von orig erstellt.
        this(false);
    }

    @Override
    public boolean addVertex(K id) {
        if (id != null && !vertices.containsKey(id)) {
            // TODO Einfuegen des neuen Knotens in HashMap
            vertices.put(id, new Vertex<>(id));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addEdge(K fromId, K toId) {
        Vertex<K> vf = vertices.get(fromId);
        Vertex<K> vt = vertices.get(toId);
        if (vf != null && vt != null && !vf.adjList.contains(vt)) {
            return vf.addEdgeTo(vt);
        } else {
            return false;
        }
    }

    @Override
    public boolean removeEdge(K from, K to) {
        Vertex<K> vf = vertices.get(from);
        Vertex<K> vt = vertices.get(to);
        if (vf != null && vt != null && vf.adjList.contains(vt)) {
            vf.adjList.remove(vt);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getNofVertices() {
        // TODO Ersetzen Sie die folgende Zeile durch eine effizientere
        // Implementation
        return super.getNofVertices();
    }

    @Override
    public int getNofEdges() {
        // TODO Ersetzen Sie die folgende Zeile durch eine effizientere
        // Implementation
        return super.getNofEdges();
    }

    @Override
    public Set<K> getVertices() {
        return new HashSet<K>(vertices.keySet());
    }

    @Override
    public Set<K> getAdjacentVertices(K vertex) {
        Set<K> set = vertices.get(vertex).adjList.stream().map(v -> v.data)
            .collect(Collectors.toSet());
        ;
        return set;
    }

    @Override
    public Object clone() {
        return new AdjListGraph<K>(this);
    }

    @Override
    public Graph traverse(Object startVertex) {
        vertices.values().forEach(v -> v.visited = false);

        dfs(vertices.get(startVertex));

        return this;
    }

    private void dfs(Vertex<K> vertex) {
        if(!vertex.visited) {
            vertex.visited = true;
            System.out.println(vertex.data);

            for (Vertex<K> v: vertex.adjList) {
                dfs(v);
            }
        }
    }
}
